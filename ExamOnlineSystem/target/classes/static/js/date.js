function getNowDate(type,date){
      var date = date || new Date();
      var y = date.getFullYear();
      var m = date.getMonth()+1;
      var d = date.getDate();
      var h = date.getHours();
      var mm = date.getMinutes();
      var s = date.getSeconds();

      if(type === 1){
            return y + "-" + m + "-" + d;
      }
      else if(type === 2){
            return y + "-" + m + "-" + d + " "+(h>9? h:"0"+h) + ":"+(mm>9? mm:"0"+mm)+":"+(s>9? s:"0"+s);
      }
      else if(type === 3){
            return (h>9? h:"0"+h) + ":"+(mm>9? mm:"0"+mm);
      }
      else{
            return y + "-" + m + "-" + d + " "+(h>9? h:"0"+h) + ":"+(mm>9? mm:"0"+mm);
      }
}

function getCountDown(date){
      // let d1 = new Date(faultDate);
      // let d2 = new Date(completeTime);
      var stime =new Date().getTime();
      var etime = new Date(date).getTime();
      var usedTime = etime - stime;  //两个时间戳相差的毫秒数
      var days=Math.floor(usedTime/(24*3600*1000));
      //计算出小时数
      var leave1=usedTime%(24*3600*1000);    //计算天数后剩余的毫秒数
      var hours=Math.floor(leave1/(3600*1000));
      //计算相差分钟数
      var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
      var minutes=Math.floor(leave2/(60*1000));
      //计算秒
      
      var leave3 = leave2%(60*1000);  
      var seconds = Math.floor(leave3/1000);       //计算小时数后剩余的毫秒数
     
      var time = days + "天"+hours+"时"+minutes+"分"+seconds+"秒";
     
      return {days,hours,minutes,seconds};
}


function computedDateTime(faultDate,completeTime){
       let d1 = new Date(faultDate);
       let d2 = new Date(completeTime);
      var stime =d1.getTime();
      var etime = d2.getTime();
      var usedTime = etime - stime;  //两个时间戳相差的毫秒数
      var days=Math.floor(usedTime/(24*3600*1000));
      //计算出小时数
      var leave1=usedTime%(24*3600*1000);    //计算天数后剩余的毫秒数
      var hours=Math.floor(leave1/(3600*1000));
      //计算相差分钟数
      var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
      var minutes=Math.floor(leave2/(60*1000));
      //计算秒
      
      var leave3 = leave2%(60*1000);  
      var seconds = Math.floor(leave3/1000);       //计算小时数后剩余的毫秒数
     
      var time = days + "天"+hours+"时"+minutes+"分"+seconds+"秒";
     
      return {days,hours,minutes,seconds};
}

function getCountDownText(date){
      // let d1 = new Date(faultDate);
      // let d2 = new Date(completeTime);
      var stime =new Date().getTime();
      var etime = new Date(date).getTime();
      var usedTime = etime - stime;  //两个时间戳相差的毫秒数
      var days=Math.floor(usedTime/(24*3600*1000));
      //计算出小时数
      var leave1=usedTime%(24*3600*1000);    //计算天数后剩余的毫秒数
      var hours=Math.floor(leave1/(3600*1000));
      //计算相差分钟数
      var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
      var minutes=Math.floor(leave2/(60*1000));
      //计算秒
      var leave3 = leave2%(60*1000);  
      var seconds = Math.floor(leave3/1000);       //计算小时数后剩余的毫秒数
      var time = days + "天"+hours+"时"+minutes+"分"+seconds+"秒";
      var ht = hours>=10? hours : "0"+hours;
      var mt = minutes>=10? minutes: "0"+minutes;
      var st = seconds>=10? seconds: "0"+seconds;
      return ht + ":" + mt + ":" +st;
}
function friendlyCountDown(d1,d2){
      var date;
      if(d2==null){
            date  = getCountDown(d1);
      }
      else{
            date = computedDateTime(d1,d2);
      }
      

      if(date.days > 0){
            return date.days + "天";
      }
      else if(date.days == 0 && date.hours > 0 ){
            return date.hours + "小时" + date.minutes+"分";
      }
      else if(date.days == 0 && date.hours == 0){
            return date.minutes + "分"+date.seconds + "秒";
      }
      else{
            return "已经结束";
      }
}

function textDate(date){
      var d = new Date(date);
      return ( d.getMonth()+1)+"月"+d.getDate()+"日";
}

var d = '<div class="rendered-markdown"><p>某电脑组装公司规定：</p><p>每个成品电脑由多种零件组成，每种零件可以参与到多种成品电脑的组成，每种零件参与到成品电脑的组装中有一个零件使用量，成品电脑存储到仓库中，每个仓库可以存储多种电脑，每种电脑只能存放于一个仓库中，用库存量来记录该种电脑的库存量。</p><p>各实体的属性如下：</p><p>电脑：电脑型号，电脑名称，售价</p<p>零件：零件型号，零件名称，单价</p><p>仓库：仓库编号，名称，地点，电话，管理人员编号，管理人员姓名</p><p><strong>题目：</strong>使用关系代数书写：查询零件名称为“CPU”的零件在每种电脑里的使用情况，列出零件型号，电脑型号，零件使用量。（需要用到的符号可从这里拷贝: σ ∞ ∏）@<a href="2" target="_blank"></a></p><p><strong>重要提示：</strong></p><p>数据库设计的题目是要求在画出ER图后，将表格设计出来，然后在设计出来的表格上操作。此题是在表格设计出来后，要求同学完成关系代数的书写。</p><p>同学们直接拷贝题目中给出的三个符号：选择（σ），连接（∞） ，投影（∏）在答题区直接编辑答题。由于在答题区没有设置下标的功能，因此<strong>可以不使用下标表示</strong>。</p><p>比如：∏学号，课程号（σ性别="女" and 年龄&gt;18(学生）∞ （学生选课））。假设有学生表和学生选课表，该查询查出了18岁以上的女同学的选课情况，列出学号和课程号。</p>'


var demo = ' <div class="my-2 px-3 pt-2 pb-0 description_3ssj2"><div class="title_2D8rG">公告</div><div class="Expandable_62xVg isExpanded_3JEJD " style="max-height: 538px;"><div class="pb-4"><div class="rendered-markdown"><p>《数据库原理与应用》上机考试说明：</p><p><strong>考试题型</strong>包括：单选题，填空题，判断题，SQL语句题，范式题，数据库设计题</p><p>其中<strong>填空题</strong>的答案里<strong>不能</strong>出现空格（若出现空格则将判为<strong>错误</strong>，请务必注意！）</p><p>本试卷中主要出现五种类型的题：<strong>填空题</strong>，<strong>SQL语句题</strong>，<strong>数据库设计题中画ER图部分</strong>，<strong>数据库设计题中的表设计部分</strong>，<strong>关系代数</strong>。</p><p>其<strong>主要目的</strong>是让同学们了解这五种类型的题目的完成过程，请同学们务必<strong>熟练掌握其操作流程</strong>，以避免在考试过程中因为操作不熟练而损失考试时间。</p><p>在正式试卷中，主观题中<strong>不会出现</strong>该测试试卷中的“重要提示”部分，请务必要提前<strong>熟练掌握</strong>题目解答流程！</p><p>SQL语句题需要<strong>特别注意</strong>的是：</p><p>1、括号，单引号，逗号，等号等符号，<strong>必须在英文状态下输入</strong>。</p><p>2、基于系统底层判断规则，请务必在写SQL语句时，使用<strong>小写</strong>书写<strong>列名</strong>和<strong>表名</strong>！</p><p>3、凡是要求在Select子句中列出的<strong>统计列</strong>（比如平均成绩，总学分等），都要<strong>严格按题目要求为列命别名</strong>，否则会判为错误！</p><p>PS：SQL题目比较多，大家可以借助这些题多复习一下。考试题目中SQL语句题对应的<strong>表结构会不一样</strong>，因此希望大家在理解的基础上完成语句的书写。</p><p>4、在SQL语句题中，若出现了语法错误，可以看到错误提示，考试时，在<strong>时间允许的情况下</strong>，大家可以参考提示修改你的语句</p><p><img alt="SQL语句题错误自检方法.png" src="https://images.ptausercontent.com/4066101b-611a-4b3d-adfa-c2b6e1d7faeb.png"></p><p><strong>祝同学们考试顺利！</strong></p>'