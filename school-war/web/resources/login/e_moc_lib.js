//-- moc_lib.js: Core JS library for www.oracle.com
var ORA_UCM_INFO;


//-- Function Library

// to populate the user name -------------------------------------------------//
function PopulateLogin() {
    ORA_UCM_INFO  = new private_ORA_UCM_INFO();
    if (existsUCMCookie("ORA_UCM_INFO") == true) {
        var usernameTest = ORA_UCM_INFO.username;
        var resultUnameTest = usernameTest.search("undefined_");
        //var testStr = "Username = " + ORA_UCM_INFO.username + ".  Check = " + resultUnameTest;
        //alert (testStr);
        if (resultUnameTest == -1) {
          document.LoginForm.ssousername.value = ORA_UCM_INFO.username;
        } else if (resultUnameTest != -1) {
          document.LoginForm.ssousername.value = ORA_UCM_INFO.email;
        }
    }
}

function getName(){
    if (isUCMRegistered()) return ORA_UCM_INFO.firstname + " " + ORA_UCM_INFO.lastname
}

function drawHeader() {
    getUCMCookies()
    if (isUCMRegistered()) {
        block = '<font class="PortletHeading2">Welcome ' + getName() + '</font>'
    }
    document.write(block)
    document.close()
}

// UCM Cookie Libraries ----------------------------------------------------- //
function existsUCMCookie(s) {
    if (s == "ORA_UCM_VER") {
        if ((ORA_UCM_VER.value != null) &&
            (ORA_UCM_VER.version != null) &&
            (ORA_UCM_VER.username != null) &&
            (ORA_UCM_VER.username_enc != null) &&
            (ORA_UCM_VER.ipaddress != null) &&
            (ORA_UCM_VER.ipaddress_enc != null) ) {
        return true;
        }
    }
    else if (s == "ORA_UCM_INFO") {
        //MW: reduce for v2 cookies
        if ((ORA_UCM_INFO.version != null) &&
            (ORA_UCM_INFO.guid != null) &&
            (ORA_UCM_INFO.username != null)) {
        return true;
        }
    }
    else if (s == "ORA_UCM_SRVC") {
        if ((ORA_UCM_SRVC.value != null) &&
            (ORA_UCM_SRVC.version != null)) {
        }
        return true;
    }
    else if (s == "ORA_UCM_CMP") {
        if ((ORA_UCM_CMP.value != null) &&
            (ORA_UCM_CMP.version != null)) {
        }
        return true;
    }

    return false;
}
// ------------------------------------------------------------------------- //
function isUCMRegistered() {

    if ((existsUCMCookie("ORA_UCM_VER") == true) &&
        (existsUCMCookie("ORA_UCM_INFO") == true) &&
        (existsUCMCookie("ORA_UCM_SRVC") == true) ) {
        orainfo_exists = true;
        otnnm_exists = true;
        return true;
    }
    return false;
}
function getUCMCookies() {
    ORA_UCM_VER   = new private_ORA_UCM_VER();
    ORA_UCM_INFO  = new private_ORA_UCM_INFO();
    ORA_UCM_SRVC  = new private_ORA_UCM_SRVC();
    ORA_UCM_CMP   = new private_ORA_UCM_CMP();
}

//-- Cookie Functions
function private_ORA_UCM_INFO() {
  this.value_enc   = getCookieData("ORA_UCM_INFO");

  // check for new or old cookie format
  if (this.value_enc.substr(0,4) == "/MP/") { // this is version 1

    this.value       = private_UCMCookieDecode(this.value_enc);
    this.array       = this.value.split("OR1:");

    this.version      = this.array[0];
    this.guid         = this.array[1];
    this.firstname    = this.array[2];
    this.lastname     = this.array[3];
    this.username     = this.array[4];
    this.email        = this.array[5];
    this.companyname  = this.array[6];
    this.title        = this.array[7];
    this.country      = this.array[8];
    this.language     = this.array[9];
    this.characterset = this.array[10];
    this.interest1    = this.array[11];
    this.interest2    = this.array[12];
    this.interest3    = this.array[13];
    this.interest4    = this.array[14];
    this.interest5    = this.array[15];
    // MW: old cookies only support ascii first and last name
    this.ascii        = 1;


  } else { // this is version 2

    this.array       = this.value_enc.split("~");

    this.version      = this.array[0];
    this.guid         = this.array[1];
    this.firstname    = this.array[2];
    this.lastname     = this.array[3];
    this.username     = this.array[4];
    this.country      = this.array[5];
    this.language     = this.array[6];
    this.interest1    = this.array[7];
    this.interest2    = this.array[8];
    this.interest3    = this.array[9];
    this.interest4    = this.array[10];
    this.ascii        = this.array[11];
    // MW: put dummy variables into deprecated fields
    this.email        = this.username;
    this.companyname  = null;
    this.title        = null;
    this.characterset = null;
    this.interest5    = null;


  }
}


// ------------------------------------------------------------------------- //
function private_UCMCookieDecode(value) {

    var asciiArray = " !\"#$&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~.";
    var urldecodevalue = unescape(value);
    var ucmdecodevalue = '';
    var ch = '';

    for (i=0; i<urldecodevalue.length; i++) {

        ch = urldecodevalue.charAt(i)
        j = asciiArray.indexOf(ch);
        if (j != -1) {

            j +=2;
            if( j > ( asciiArray.length - 1 ) ) {
                j -= asciiArray.length;
            }
            ucmdecodevalue += asciiArray.charAt( j );
        }
        else {
            ucmdecodevalue += ch;
        }
    }
    return ucmdecodevalue;
}

// ------------------------------------------------------------------------- //
function getCookieData(label) {
var labelLen = label.length
var cLen = document.cookie.length
var i = 0
var cEnd
    while (i < cLen) {
    var j = i + labelLen
    if (document.cookie.substring(i,j) == label) {
        cEnd = document.cookie.indexOf(";",j)
        if (cEnd == -1) {
            cEnd = document.cookie.length
        }
    j++;
    return unescape(document.cookie.substring(j,cEnd))
    }

    i++
    }

    return "";
}
// ------------------------------------------------------------------------- //