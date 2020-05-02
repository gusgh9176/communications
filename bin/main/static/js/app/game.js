//var userName = $(".userInfo name").val();
//var userId = $(".userInfo id").val();
//var userId = "papa";		//테스트용 임시 id


var data = {
		id: "userId"
};


$(document).ready(function() {

	$("#header").load("userInfo #userId");
	
	
	//userInfo.html의 userId를 불러와 #header에 삽입해준다.
});


	$(function() {
		var cardUI = $('#cardUI');
    	var cardNum = $('#cardNum');
    	var userName = $("#userId").text();

    	var isFirst = true;
    	var selectedNum = 0;
		
    	var gameHash = 0;
		var cards = new Array(1, 2, 3, 4, 5, 6, 7, 8);
		
		//getGameHash();
    	showCard();
    	//
    	showMsg(userName);
    	
    	function showCard() {
    		for(var i=0; i < cards.length; i++) {
          		if(cards[i]%2!=1) {
            		cardUI.append("<div class='card odd'>" + cards[i] + "</div>");
          		}
          		else cardUI.append("<div class='card even'>" + cards[i] + "</div>");
        	}
    	}
    	
    	$('.card').click(function() {
    	    var num = $(this).val();
    	    this.cardNum.val(this.num);
    	});
    	
    	$('#selectBtn').click(function() {
    		this.selectedNum = this.cardNum.val();
    		$('#selectForm').css('visibility', 'hidden');
    		cards.splice(cards.indexOf(selectedNum, 1));
    		postCardNum();
    	});
    	
    	function showMsg(message) {
    		$('#infoText').text(message);
    		$('#infoText').css('visibility', 'visible');
    	}
    	
    	function selectCard() {
    		$('#selectForm').css('visibility','visible');
    	}
    	
    	
    	function afterRound() {
    		showCard();
    		if(this.isFirst == true) {
    			showMsg("선공입니다. 카드를 골라주세요");
    			selectCard();
    		}
    		else {
    			showMsg("상대의 선택을 기다리는 중");
    			getColor();
    		}
    	}
    	
    	function getGameHash() {
    		//showMsg("매칭 중입니다...");
    		$.ajax({
          		type : "GET",
          		dataType : "json",
          		contentType : "application/json; charset=utf-8",
          		url : "/match",
          		data : JSON.stringify(data),
          		success : function(gameHash) {
    				this.gameHash = gameHash;
    				$('#selectForm').css('visibility','visible');
          		},
          		timeout : 3000,
          		error : function(e) {
          			//getGameHash();
          			//alert(userName);
          		}
    			//post로 데이터 전송 get으로 롱풀링받아오기
        	});
    	}
    	
    	function postCardNum() {
        	$.ajax({
          		type : "POST",
          		dataType : "json",
          		url : "/game/cardnum",
          		data : JSON.stringify({user: userId, selectCard: selectedNum, gameHash: gameHash}),
          		success: function(winner) {
            		if(this.isFirst == true) {
            			getWinner();
            		}
            		else {
            			if(winner == "Game End") {
          					getDualResult();
          				}
          				else if(winner == this.id) {
          					this.isFirst = true;
          					//selectCardNum
          				}
          				else {
          					this.isFirst = false;
          				}
            		}
            		afterRound();
          		}
        	});
    	}

      	function getColor() {
        	$.ajax({
          		type : "GET",
          		dataType : "json",
          		url : "/game/color",
          		data : JSON.stringify({gameHash: gameHash}),
          		success : function(color) {
    				showMsg("상대가 고른 카드는" + color + "입니다");
    				selectCard();
          		},
          		timeout : 3000,
          		error : function(e) {
    				getColor();
          		}
    			//post로 데이터 전송 get으로 롱풀링받아오기
        	});
      	}
      	
      	function getWinner() {
      		$.ajax({
      			type : "GET",
      			datatype : "json",
      			url : "/game/winner",
      			data : JSON.stringify({gameHash: gameHash}),
      			success : function(winner) {
      				if(winner == "Game End") {
      					getDualResult();
      				}
      				else if(winner == this.id) {
      					this.isFirst = true;
      					//selectCardNum
      				}
      				else {
      					this.isFirst = false;
      				}
      				afterRound();
      			},
      			timeout : 3000,
      			error : function(e) {
      				getWinner();
      			}
      		});
      	}
      	
      	function getDualResult() {
      		$.ajax({
      			type : "GET",
      			datatype : "json",
      			url : "/game/dualResult",
      			data : JSON.stringify({gameHash: gameHash}),
      			success : function(result) {
      				if(result == this.id) {
      					alert("You win!");
      				}
      				else {
    					alert("Defeated..");
      				}
      			}
      		});
      	}
    	
	});
