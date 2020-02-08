$(function() {
		var messageBox = $('#messageBox');
		var messageInput = $('#messageInput');
		var sendBtn = $('#sendBtn');
		var name = "user";
		
		var socket = new SockJS('/websocket');	
		var client = Stomp.over(socket);		//SockJS와 client 연결
		
		
		client.connect({}, function () {	//connection시 실행
			
			//메인 채팅 구독
			client.subscribe('/topic/chat', function (chat) {
		    	var content = JSON.parse(chat.body);
		    	messageBox.append("<li>" + content.name + " : " + content.message + "</li>")
			});
			//귓속말 구독
			client.subscribe('/queue/' + name, function (chat) {
				var content = JSON.parse(chat.body);
				messageBox.append("<li class='whisper'>" + content.name + ">>" + content.message + "</li>")
			})
			
			client.send("/app/join", {}, JSON.stringify({name: name, message: ""}));
		});
		
		//전송버튼 이벤트 등록
		sendBtn.click(function() {
			var message = messageInput.val();
			if(isWhisper(message) == true) {
				client.send("/app/whisper", {}, convertWhisper(message));
			}
			else client.send("/app/chat", {}, JSON.stringify({name: name, message: message}));
			messageInput.val("");
		});
		
		
		function isWhisper(message) {
			if(message[0] == "/") return true;
			else return false;
		}
		
		function convertWhisper(message) {
			var nameIndex = message.indexOf(" ");
			var receiver = message.slice(1, nameIndex);
			var newMessage = message.slice(nameIndex+1, message.length);
			return JSON.stringify({name: name, message: newMessage, receiver: receiver});
		}
		
});

