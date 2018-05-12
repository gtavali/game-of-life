var boardSize = 30;
var boxSize;
var actualCells;

var canvas;
var ctx;

var play = false;

function initializeCanvas() {
$(document).ready(function() {
       canvas = document.getElementById("canvas");
       canvas.addEventListener('click', handleClick);
       ctx = canvas.getContext('2d');

       boxSize = Math.floor(canvas.width / boardSize);

       drawEmptyCanvas();
    });
}

function handleClick(e) {
   ctx.beginPath();
   ctx.fillStyle = "black";
   var row = Math.floor(e.offsetY/boxSize)*boxSize;
   var column = Math.floor(e.offsetX/boxSize)*boxSize;
   ctx.fillRect(column, row, boxSize, boxSize);
   ctx.closePath();

    var cell = new Object();
    cell.row = row / boxSize;
    cell.column  = column / boxSize;

    if (typeof actualCells == 'undefined') {
       actualCells = [];
    }
    actualCells.push(cell);
}

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

function paintCanvas(cells, color) {
    ctx.beginPath();
   for (var i = 0; i < cells.length; i++) {
       var cell = cells[i];
      ctx.fillStyle = color;
      ctx.rect(cell.column * boxSize, cell.row * boxSize, boxSize, boxSize);
      ctx.fill();
      ctx.stroke();
   }
   ctx.closePath();
}

function getNextGeneration() {
    $(document).ready(function(){
        $.ajax({
          url: "http://localhost:8090/backend/next-generation/" + boardSize,
          type: "POST",
          data: JSON.stringify(actualCells),
          contentType: 'application/json',
          success: function(data){
            paintCanvas(actualCells, "white");
            paintCanvas(data, "black");
            actualCells = data;
          }
        })
    });
}

$(document).ready(function(){
    $("button.nextgeneration").click(function(){ getNextGeneration(); });

    $("button.play").click(function(){
        play = setInterval(function(){ getNextGeneration(); }, 1000);
    });

    $("button.stop").click(function(){
        clearInterval(play);
    });

    $("button.clear").click(function(){
        paintCanvas(actualCells, "white");
        actualCells = [];
    });

    $(document).on('change', '#slider', function() {
        boardSize = $(this).val();
        $('#slider_value').html(boardSize);
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        initializeCanvas();
    });

});


