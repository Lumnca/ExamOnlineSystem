const IP = "127.0.0.1";
const PORT = "100";
const HOST = "http://" + IP + ":" + PORT;
const FS = "http://47.106.254.86:66";
const UPLOADFILE = HOST+ "/uploadimg";
function getUserInfo() {
    return JSON.parse(window.localStorage.getItem('_user'))? JSON.parse(window.localStorage.getItem('_user')):{username:'2017110329测试'};
}

function getOneUrlStr(str){
    return window.location.search.split(str+"=").pop();
}

function reLogin() {

}


function request(method, path, reqparams, then, error ,timeout) {

    if (error == null) {
        error = function (e) {
            console.log(e);
        }
    }
    if (then == null) {
        then = function (res) {
            console.log("Requset OK!");
        }
    }

    if(timeout){
        if(isNaN(timeout))timeout=1000;
        setTimeout(()=>{
            if (method === 'GET' || method === 'get') {
                axios.get(HOST + path, { params: reqparams }).then(then).catch(error);
            }
            else if (method === 'POST' || method === 'post') {
                axios.post(HOST + path, reqparams).then(then).catch(error);
            }
            else {
                axios.get(HOST + path, { params: reqparams }).then(then).catch(error);
            }
        },timeout);
    }
    else{
        if (method === 'GET' || method === 'get') {
            axios.get(HOST + path, { params: reqparams }).then(then).catch(error);
        }
        else if (method === 'POST' || method === 'post') {
            axios.post(HOST + path, reqparams).then(then).catch(error);
        }
        else {
            axios.get(HOST + path, { params: reqparams }).then(then).catch(error);
        }
    }

    
}


function warnconfirm(app, txt, then) {
    app.$confirm(txt, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
    }).then(then).catch(() => {
        app.$message({
            type: 'info',
            message: '已取消操作'
        });
    });
}

function responseType(app, data, success, error) {
    if (data.code == 200) {
        app.$message({
            message: data.message || success,
            type: 'success'
        });
    }
    else if (data.code == 400) {
        app.$message.error(data.message || error);
    }
    else {
        app.$message({
            type: 'info',
            message: data.message
        });
    }
}

function notnull(data) {
    if (data == null || data == undefined || data == '') {
        return false;
    }
    else {
        return true;
    }
}

function relaodView(s, herf) {
    s ? s : 0;

    if (herf) {
        setTimeout(function () {
            window.location.href = herf;
        }, s);
    }
    else {
        setTimeout(function () {
            window.location.reload();
        }, s);
    }
}

function loadEditor(id,h){
    let height = h || 200;
    let E = window.wangEditor;
    let editor = new E(id);
    editor.config.height = height;
    editor.config.uploadImgServer = HOST+ '/uploadimgeditor'
    editor.config.uploadFileName = 'upload'
    editor.create();
    return editor;
}


function randomOxStr(){
    var str = ['A','B','C','D','E','F','0','1','2','3','4','5','6','7','8','9']
    var randomstr = '';
    for (let i= 0; i<16; i++) {
       randomstr += str[Math.floor(Math.random()*str.length)];
        if((i+1)%4==0&&i+1!=16){
            randomstr += '-';
        }
    }
    return randomstr;
}

function randomStr(){
    var str = ['0','1','2','3','4','5','6','7','8','9']
    var randomstr = '';
    for (let i=0; i<4; i++) {
       randomstr += str[Math.floor(Math.random()*str.length)];
    }
    return randomstr;
}


