//var userName = $(".userInfo name").val();
//var userId = $(".userInfo id").val();
//var userId = "papa";		//테스트용 임시 id


	$(function() {
		$("#header").load("userInfo #userId");
		
		var cardUI = $('#cardUI');
    	var cardNum = $('#cardNum');
    	//var userId = $("#userId").text();
    	var userId = prompt("userId?");

    	var isFirst = true;
    	var selectedNum = 0;
		
    	var gameHash = 0;
		var cards = new Array(1, 2, 3, 4, 5, 6, 7, 8);
		
		getGameHash();
    	showCard();
    	//$("#userId").val("change");
    	//alert(this.userName);
    	//showMsg(this.userName);
    	
    	function showCard() {
    		$('#cardUI').empty();
    		
    		for(var i=0; i < cards.length; i++) {
          		if(cards[i]%2!=1) {
            		cardUI.append("<div class='card odd'>" + cards[i] + "</div>");
          		}
          		else cardUI.append("<div class='card even'>" + cards[i] + "</div>");
        	}
    	}
    	
    	$('.card').click(function() {
    	    var num = $(this).text();
    	    $("#cardNum").val(num);
    	});
    	
    	$('#selectBtn').click(function() {
    		this.selectedNum = $("#cardNum").val();
    		$('#selectForm').css('visibility', 'hidden');
    		cards.splice(cards.indexOf(Number(this.selectedNum)), 1);
    		showCard();
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
          		contentType : "application/json; charset=UTF-8",
          		url : "/match",
          		data : { id:userId },
          		success : function(Hash) {
          			alert(Hash);
    				this.gameHash = Hash;
    				$('#selectForm').css('visibility','visible');
          		},
          		timeout : 3000,
          		error : function(e) {
          			getGameHash();
          		}
    			//post로 데이터 전송 get으로 롱풀링받아오기
        	});
    	}
    	
    	function postCardNum() {
        	$.ajax({
          		type : "POST",
          		dataType : "json",
          		contentType : "application/json; charset=UTF-8",
          		url : "/game",
          		data : JSON.stringify({user: userId, selectCard: selectedNum, gameHash: gameHash}),
          		success: function(winner) {
            		if(this.isFirst == true) {
            			getWinner();
            		}
            		else {
            			if(winner == "Game End") {
          					getDualResult();
          				}
          				else if(winner == this.userId) {
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
          		contentType : "application/json; charset=UTF-8",
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
      			contentType : "application/json; charset=UTF-8",
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
      			contentType : "application/json; charset=UTF-8",
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
