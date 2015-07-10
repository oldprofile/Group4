var messageAccount = function (name, password){
	return{
		user : name,
		parole : password
	};
};

function run(){
	var log_in = document.getElementById('log');
	log_in.addEventListener ('click', EventLogIn);
}

function defaultErrorHandler(message) {

}
function EventLogIn(evtObj){
	var uname = document.getElementById('username');
	var pass = document.getElementById('password');
	if (uname.value&&pass.value){
//		alert('Good job, Mladka!');
	    var sendData = messageAccount(uname.value, pass.value);
		uname.value = "";
		pass.value = "";
//        debugger
		postAccount(sendData, function(){});
	}
}

function postAccount(message, continueWith){
	post("http://localhost:8080/authentication/log_password", JSON.stringify(message), function(){
		
	});
}


function post(url, data, continueWith, continueWithError) {
	ajax('POST', url, data, continueWith, continueWithError);	
}


function ajax(method, url, data, continueWith, continueWithError) {
	var xhr = new XMLHttpRequest();

	continueWithError = continueWithError || defaultErrorHandler;

	xhr.open(method || 'GET', url, true);

    xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function () {
		if (xhr.readyState !== 4)
			return;

		if(xhr.status != 200) {
            debugger
			continueWithError('Error on the server side, response ' + xhr.status);
			return;
		}
        debugger
		if(isError(xhr.responseText)) {
			continueWithError('Error on the server side, response ' + xhr.responseText);
			return;
		}

		continueWith(xhr.responseText);
	};    

    xhr.ontimeout = function () {
    	ontinueWithError('Server timed out !');
    }

    xhr.onerror = function (e) {
    	var errMsg = 'Server connection error !\n'+
    	'\n' +
    	'Check if \n'+
    	'- server is active\n'+
    	'- server sends header "Access-Control-Allow-Origin:*"';

        continueWithError(errMsg);
    };

    xhr.send(data);
}