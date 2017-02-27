
//global js var
var isNav;

// on load, run this
function doLoad() {

    MM_reloadPage(true);
    isNav = (navigator.appName.indexOf("Netscape") !=-1);

    //register event listeners
    //document.onkeypress = getKey;
    if (isNav)
    {
        document.captureEvents(Event.KEYPRESS);
        document.LoginForm.password.onkeypress = getKey;
        document.LoginForm.ssousername.onkeypress = getKey;
    }
    else
    {
        document.onkeypress = getKey; //Bug 10331785
    }
    //different from customized login page
    PopulateLogin();
}

//use cookie to populate login
function PopulateLogin() {
    ORA_UCM_INFO  = new private_ORA_UCM_INFO();
    var uname = document.LoginForm.ssousername.value;

    //the username could be poplated by some data, don't overwrite it if it is
    if ((uname == "") && (existsUCMCookie("ORA_UCM_INFO") == true)) {
		document.LoginForm.ssousername.focus();
        document.LoginForm.ssousername.value = ORA_UCM_INFO.username;
    }
}

//registered event listener, passes all key ASCII values to checkKey method
function getKey(keyStroke) {
    keyHit = (isNav) ? keyStroke.which : event.keyCode;
    whichKey = String.fromCharCode(keyHit).toLowerCase();
    checkKey(keyHit);
}

//if this method is passed the return key (13) char, then do a login
function checkKey(key) {
  if (key=="13")
  {
    doLogin(document.LoginForm);
  }
}

//reloads the window if Nav4 resized
function MM_reloadPage(init) {
    if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
        document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
    else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}

//check if the given string contains "@oracle" or "@oracle.com" in any cAsE
function isAtOracle (s) {
    var tester = s.toLowerCase();
    var minString = "@oracle";
    var maxString = "@oracle.com";

    var atOffset = tester.indexOf(minString);
    if (atOffset >= 0) {
        //test all portions of @oracle.com
        for (var i = atOffset + minString.length; i < tester.length; i++) {
            //alert("tester = " + tester.charAt(i) + "\nmaxString = " + maxString.charAt(i - atOffset));
            if (tester.charAt(i) != maxString.charAt(i - atOffset)) {
                //alert ("different");
                return false;
            }
        }
        return true;
    }
    return false;
}

//test the username element of a form and correct it if it's oracle or fail
function isValidUsername(form) {
    var username = form.ssousername.value;

    //don't hit our server if your not sending data
	// Let OAM check for null attributes.
    /*if (username == "") {
        alert('Please enter a Username.');
        form.ssousername.focus();
        return false;
    }*/

    //determine if an email address was entered
    var atIndex = username.indexOf("@");
    if (atIndex >= 0) {
        //if it's an oracle name, format everything after @
        if (isAtOracle(username)) {
            username = username.substring(0, atIndex);
            username = username + "@oracle.com";
        } else {
            //non oracle.com user's can't login here
            //alert("To access this site, please log in using your short username instead of email address.");
            //form.username.focus();
            //return false;
        }
    }
    //the username is fine, or we've reformatted an Oracle name
    form.ssousername.value = username;
    return true;
}

// check if username or password is null
function isEmpty(form) {
    var username = form.ssousername.value;
    var password = form.password.value;
     if (username == "" || password == "") {
        document.getElementById('required').className = "error-show";
        document.getElementById('readerunamerequired').className = "error-show";
        document.getElementById('readerpwdrequired').className = "error-show";
        try
        {
            document.getElementById('errormsg').className = "error-hide";
            document.getElementById('readerunameerrormsg').className = "error-hide";
            document.getElementById('readerpwderrormsg').className = "error-hide";
        }
        catch (err)
        {
        }
        setFocus();
        return true;
    }
}

//perform a login for the LoginForm
function doLogin(form) {
    // check if username or password is null
    if (isEmpty(form)) {
        return;
    }
    
//    This method can be used to check for special charecteristics when such a 
//    requirement comes up.
//    if (!isValidUsername(form)) {
//        return;
//    }

    //obfuscation of vars
    //form.uname.value = form.username.value;
    //form.pwd.value = (form.pwd.value == "") ? form.password.value : form.pwd.value;   //this line is required for IE5.5, which fires doLogin twice.
    //form.username.value = "";
    //form.password.value = "";

    form.submit();
    return false;
}
// function to strip crossSite script in URLs
function stripHTML()
{
	var orgURL=location.href;
	var tempURL=orgURL;
	orgURL.replace(/[\"\'][\s]*javascript:(.*)[\"\']/g, "\"\"");
    orgURL = orgURL.replace(/%22%3E%3Cscript(.*)/g, "");
	orgURL = orgURL.replace(/script(.*)/g, "");
	orgURL = orgURL.replace(/eval\((.*)\)/g, "");
	if(tempURL.length!= orgURL.length)
      location.href=orgURL;

}

// set the focus on password field if username exists
function setFocus()
{  
    var username = document.LoginForm.ssousername.value;
          
    if(username != null && username != "")
    {
      document.LoginForm.password.focus();
    }
    else
    {
      document.LoginForm.ssousername.focus()
    }
}
//-->