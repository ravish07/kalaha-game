
$(document).ready(function() {

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/kalah/start',
        dataType: "json"
    }).then(function(game) {
        $('#status').text(game.status);
        var board = game.board;
          $('#0').text(board[0]);
          $('#1').text(board[1]);
          $('#2').text(board[2]);
          $('#3').text(board[3]);
          $('#4').text(board[4]);
          $('#5').text(board[5]);

          $('#7').text(board[7]);
          $('#8').text(board[8]);
          $('#9').text(board[9]);
          $('#10').text(board[10]);
          $('#11').text(board[11]);
          $('#12').text(board[12]);

          $('kalah-1').text(board[6]);
          $('kalah-2').text(board[12]);

    }); // .error 

    $(".pit").click(function(){

      var uiBoard =   $(".pit");
      var board = [];
      var pit = this.id;

      board[6]=  $('#kalah-1')[0].innerHTML;
      board[13]=  $('#kalah-2')[0].innerHTML;

      var index, len;
      for (index = 0 ; index < uiBoard.length; ++index) {
         board[uiBoard[index].id]=uiBoard[index].innerHTML
      }

      var gamejson = {
             "board": board ,
             "status": $('#status')[0].innerHTML,
             "message": "Welcome to Game of Kalah.",
             "player1": {
                      "id": 1,
                      "kalah": 6
                    },
              "player2": {
                      "id": 2,
                      "kalah": 13
                    }
              } ;


      var gamePlay = {
        "pit" :  pit ,
        "game" : gamejson

      };

      $.ajax({
          type: 'POST',
          url: 'http://localhost:8080/kalah/play',
          dataType: "json",
          contentType: "application/json",
          data : JSON.stringify(gamePlay)
      }).then(function(game) {
          $('#status').text(game.status);
          var board = game.board;
            $('#0').text(board[0]);
            $('#1').text(board[1]);
            $('#2').text(board[2]);
            $('#3').text(board[3]);
            $('#4').text(board[4]);
            $('#5').text(board[5]);

            $('#7').text(board[7]);
            $('#8').text(board[8]);
            $('#9').text(board[9]);
            $('#10').text(board[10]);
            $('#11').text(board[11]);
            $('#12').text(board[12]);

            $('#kalah-1').text(board[6]);
            $('#kalah-2').text(board[13]);
      });
    });

});
