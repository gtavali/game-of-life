/*
The main js to serve FE and to manage BE calls.

Created by Gabor Tavali.

*/
var boardSize = 30;
var boxSize;
var actualCells;

var canvas;
var ctx;

var play = false;

//Initialize the canvas when the FE loads.
function initializeCanvas() {
    $(document).ready(function() {
        canvas = document.getElementById("canvas");
        canvas.addEventListener('click', handleClick);
        ctx = canvas.getContext('2d');

        boxSize = Math.floor(canvas.width / boardSize);

        drawEmptyCanvas();
    });
}

//Handle the click event on the board.
function handleClick(e) {
    ctx.beginPath();
    ctx.fillStyle = "black";
    var row = Math.floor(e.offsetY / boxSize) * boxSize;
    var column = Math.floor(e.offsetX / boxSize) * boxSize;
    ctx.fillRect(column, row, boxSize, boxSize);
    ctx.closePath();

    var cell = new Object();
    cell.row = row / boxSize;
    cell.column = column / boxSize;

    if (typeof actualCells == 'undefined') {
        actualCells = [];
    }
    actualCells.push(cell);
}

//Draw an empty canvas.
function drawEmptyCanvas() {
    ctx.beginPath();
    ctx.fillStyle = "white";
    ctx.lineWidth = 1;
    ctx.strokeStyle = 'black';

    for (var row = 0; row < boardSize; row++) {
        for (var column = 0; column < boardSize; column++) {
            var x = column * boxSize;
            var y = row * boxSize;
            ctx.rect(x, y, boxSize, boxSize);
            ctx.fill();
            ctx.stroke();
        }
    }

    ctx.closePath();
}

//Paint the canvas with the given cells and color. Black means a cell alive, white means a cell no more.
function paintCanvas(cells, color) {
    var paintedCells = [];
    ctx.beginPath();
    for (var i = 0; i < cells.length; i++) {
        var cell = cells[i];
        if (cell.row < boardSize && cell.column < boardSize) {
            ctx.fillStyle = color;
            ctx.rect(cell.column * boxSize, cell.row * boxSize, boxSize, boxSize);
            ctx.fill();
            ctx.stroke();
            paintedCells.push(cell);
        }
    }
    ctx.closePath();
    actualCells = paintedCells;
}

//Call BE to compute the next generation.
function getNextGeneration() {
    $(document).ready(function() {
        $.ajax({
            url: "http://localhost:8090/backend/next-generation/" + boardSize,
            type: "POST",
            data: JSON.stringify(actualCells),
            contentType: 'application/json',
            success: function(data) {
                paintCanvas(actualCells, "white");
                paintCanvas(data, "black");
            }
        })
    });
}

//Buttons ans slider setup
$(document).ready(function() {
    $("button.nextgeneration").click(function() {
        getNextGeneration();
    });

    //The delay is 1s when the board is playing.
    $("button.play").click(function() {
        play = setInterval(function() {
            getNextGeneration();
        }, 1000);
    });

    $("button.stop").click(function() {
        clearInterval(play);
    });

    $("button.clear").click(function() {
        paintCanvas(actualCells, "white");
        actualCells = [];
    });

    $(document).on('change', '#slider', function() {
        boardSize = $(this).val();
        $('#slider_value').html(boardSize);
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        initializeCanvas();
    });

    $("button.save").click(function() {
        var generationName = $("input:text").val();

        if ("" === generationName) {
            alert("Please give a name of your pattern!");
            return;
        }

        var generation = {
            name: generationName,
            cells: actualCells,
            rowSize: boardSize,
            columnSize: boardSize
        };

        //BE call to save a custom generation.
        $.ajax({
            url: "http://localhost:8090/backend/generation",
            type: "POST",
            data: JSON.stringify(generation),
            contentType: 'application/json',
            success: function(data) {
                alert("You have successfully saved your pattern named " + generation.name);
                clearDropdown();
                populateDropdown();
                $("input:text").val("");
            },
            error: function(data) {
                alert("The name already exists. Please choose an other one!");
            }
        })

    });

});

//Populate dropdown with the name of the persisted generations including the custom patterns as well.
function populateDropdown() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8090/backend/generation/names",
        dataType: "json",
        success: function(data) {
            var dropdown = document.getElementById("dropdown");
            var option = document.createElement("OPTION");
            option.innerHTML = 'Select a pattern!';
            option.value = 'Select a pattern!';
            dropdown.options.add(option);
            for (var i = 0; i < data.length; i++) {
                var generation = data[i];
                var option = document.createElement("OPTION");
                option.innerHTML = generation;
                option.value = generation;
                dropdown.options.add(option);
            }
        }
    });
}

//Clear the dropdown.
function clearDropdown() {
    var i;
    for (i = document.getElementById("dropdown").options.length - 1; i >= 0; i--) {
        document.getElementById("dropdown").remove(i);
    }
}

//Load the chosen pattern from the database.
function loadPattern() {
    var name = $('#dropdown').val();

    $.ajax({
        type: "GET",
        url: "http://localhost:8090/backend/generation/" + name,
        dataType: "json",
        success: function(data) {
            if (data.rowSize > boardSize || data.columnSize > boardSize) {
                alert("The " + name + " size is " + data.rowSize + "x" + data.columnSize + " Please increase the size of the board to see the whole pattern.");
            }
            if (typeof actualCells == 'undefined') {
                actualCells = [];
            }
            paintCanvas(actualCells, "white");
            paintCanvas(data.cells, "black");
        }
    });
}