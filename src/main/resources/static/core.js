var boardSize;
var boxSize;
var actualCells;

var canvas;
var ctx;

function initializeCanvas() {
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8090/backend/board-size"
    }).then(function(data) {
       canvas = document.getElementById("canvas");
       canvas.addEventListener('click', handleClick);
       ctx = canvas.getContext('2d');

       boardSize = data;
       boxSize = Math.floor(canvas.width / boardSize);

       drawEmptyCanvas();

       });
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

    for (var row = 0; row <= boardSize; row++) {
       for (var column = 0; column <= boardSize; column++) {
           var x = column * boxSize;
           var y = row * boxSize;
           ctx.rect(x, y, boxSize, boxSize);
           ctx.fill();
           ctx.stroke();
       }
    }

    ctx.closePath();
}

function paintCanvas(cells) {
    ctx.beginPath();
   for (var i = 0; i < cells.length; i++) {
       var cell = cells[i];
      ctx.fillStyle = "black";
      ctx.fillRect(cell.column * boxSize, cell.row * boxSize, boxSize, boxSize);
   }
   ctx.closePath();
}

function getNextGeneration() {
    $(document).ready(function(){
        $.ajax({
          url: "http://localhost:8090/backend/next-generation",
          type: "POST",
          data: JSON.stringify(actualCells),
          contentType: 'application/json',
          success: function(data){
                drawEmptyCanvas();
                paintCanvas(data);
                actualCells = data;
          }
        })
    });
}

$(document).ready(function(){
    $("a.nextgeneration").click(function(){ getNextGeneration(); });
});


