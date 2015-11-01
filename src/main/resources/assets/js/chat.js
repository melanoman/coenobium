function getNewChats() {
    $.ajax({
        type:'GET',
        dataType: 'json',
        url: "/message/get/chat_2/3",
        data: { since : 5 },
        success: function(chats) {
            chats.forEach(function(chat) {
                // <div class="chatline">
                var chatline = document.createElement("DIV");
                var chatlineatt = document.createAttribute("class");
                chatlineatt.value = "chatline";
                chatline.setAttributeNode(chatlineatt);

                // <span class="chatsender" th:text="${chat.text[sender]}"/>

                var nametag = document.createElement("SPAN");
                var textnode = document.createTextNode(chat.text['sender']);
                nametag.appendChild(textnode);
                var nametagatt = document.createAttribute("class");
                nametagatt.value = "chatsender";
                nametag.setAttributeNode(nametagatt);

                // <span class="chatmsg" th:text="${chat.text[msg]}"/>
                var chatbody = document.createElement("SPAN");
                textnode = document.createTextNode(chat.text['msg']);
                chatbody.appendChild(textnode);
                var chatbodyatt = document.createAttribute("class");
                chatbodyatt.value = "chatmsg";
                chatbody.setAttributeNode(chatbodyatt);

                chatline.appendChild(nametag);
                chatline.appendChild(chatbody);

                var parent = document.getElementById("chatBlock");
                parent.appendChild(chatline);
            });
        }
    });
}