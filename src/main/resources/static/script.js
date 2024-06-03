let stompClient = null;

function connect() {
    let socket = new SockJS("/server1");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("Connected: " + frame);
        $("#name-form").hide();
        $("#chat-room").show();

        stompClient.subscribe("/topic/return-to", function (response) {
            console.log("Message received: " + response.body);
            showMessage(JSON.parse(response.body));
        });
    }, function (error) {
        console.error("STOMP error: " + error);
    });
}

function showMessage(message) {
    console.log("Displaying message: " + message.content);
    $("#message-container-table").prepend(`<tr><td><b>${message.name} :</b> ${message.content}</td></tr>`);
}

function sendMessage() {
    let content = $("#message-value").val();
    if (content.trim() === "") {
        alert("Message cannot be empty");
        return;
    }
    let jsonOb = {
        name: localStorage.getItem("name"),
        content: content
    };
    stompClient.send("/app/message", {}, JSON.stringify(jsonOb));
    $("#message-value").val(''); // Clear the input after sending
}

$(document).ready(function () {
    $("#login").click(function () {
        let name = $("#name-value").val();
        if (name.trim() === "") {
            alert("Please enter your name");
            return;
        }
        localStorage.setItem("name", name);
        $("#name-title").html(`Welcome, <b>${name}</b>`);
        connect();
    });

    $("#send-btn").click(function () {
        sendMessage();
    });

    $("#logout").click(function () {
        localStorage.removeItem("name");
        if (stompClient !== null) {
            stompClient.disconnect();
            $("#name-form").show();
            $("#chat-room").hide();
            console.log("Disconnected");
        }
    });

    $("#message-value").keypress(function (e) {
        if (e.which == 13) { // Enter key pressed
            sendMessage();
        }
    });
});
