const IP = "47.106.254.86";
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


function request(method, path, reqparams, then, error) {

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

    if (method === 'GET' || method === 'get') {
        axios.get(HOST + path, { params: reqparams }).then(then).catch(error);
    }
    else if (method === 'POST' || method === 'post') {
        axios.post(HOST + path, reqparams).then(then).catch(error);
    }
    else {

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


var exam = [
    {
        title: '<p><font size="5">线性表的顺序存储结构具有三个弱点：其一，在作插入或删除操作时，需移动大量元素；其二，由于难以估计，必须预先分配较大的空间，往往使存储空间不能得到充分利用；其三，表的容量难以扩充。线性表的链式存储结构是否一定都能够克服上述三个弱点，试讨论之。</font><br/></p>',
        options: ["A.一对多关系", "B.多对多关系", "C.多对一关系", "D.一对一关系"],
        style: 2,
        type: 1,
        select : ''
    },
    {
        title: '下列关于数据的逻辑结构的叙述中，不正确的是（   ）',
        options: ["A.数据的逻辑结构是数据间关系的描述", "B.数据的逻辑结构抽象反映数据元素间的逻辑关系", "C.数据的逻辑结构具体反映数据在计算机中的存储方式	", "D.数据的逻辑结构分为线性结构和非线性结构"],
        style: 1,
        type: 1,
        select : ''
    },
    {
        title: '完成在非空双向循环链表结点p之后插入s的操作是（  ）。',
        options: ["A.p->next=s ; s->prior=p; p->next->prior=s ; s->next=p->next;", "B.p->next->prior=s; p->next=s; s->prior=p; s->next=p->next;", 
        "C. s->prior=p; s->next=p->next; p->next=s; p->next->prior=s ;", "D.s->next=p->next; p->next->prior=s  ; s->prior=p;  p->next=s;"],
        style: 3,
        type: 1,
        select : ''
    },
    {
        title: '为了方便地插入和删除数据，可以使用双向链表存放数据。',
        type: 2,
        select : ''
    },
    {
        title : '线性表的顺序存储结构具有三个弱点：其一，在作插入或删除操作时，需移动大量元素；其二，由于难以估计，必须预先分配较大的空间，往往使存储空间不能得到充分利用；其三，表的容量难以扩充。线性表的链式存储结构是否一定都能够克服上述三个弱点，试讨论之。',
        type : 3,
        input : ''
    },
    {
        title : '<p>1.后缀算式9 2 3 +- 10 2 / - 的值为__-1________。中缀算式（3+4X）-2Y/3对应的后缀算式为_______ 3 4 X * + 2 Y * 3 / -_ <b>优先级高的出栈，优先级低的入栈，_入栈了一个优先级相同的，出栈，后来的那个就入栈</b>______________________。<br/></p>'
        ,type : 4,
        number : 2,
        input : []
    }
]

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


