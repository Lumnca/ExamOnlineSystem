package app.server.webapi;

import app.dao.UserDao;
import app.dao.UserInfoDao;
import app.dao.student.StudentExamDao;
import app.dao.teacher.ClassesDao;
import app.dao.teacher.ExamDao;
import app.dao.teacher.TestPaperDao;
import app.datamodel.common.Response;
import app.datamodel.common.UserInfo;
import app.datamodel.exam.Part;
import app.datamodel.exam.Result;
import app.datamodel.security.User;
import app.datamodel.student.MyExam;
import app.datamodel.student.StuExam;
import app.datamodel.teacher.*;
import app.mapper.StudentExamMapper;
import app.server.ResponseServer;
import app.server.UserServer;
import app.server.exam.AutoCheckSubject;
import app.server.exam.GetExamSubject;
import app.server.exam.LoadStudentSubjects;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataBaseRequest {
    @Autowired
    ClassesDao classesDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ResponseServer response;
    @Autowired
    TestPaperDao tpDao;
    /**
     * 班级请求
     */
    @GetMapping("classes/{id}")
    public List<Classes> getAllClassesByUid( @PathVariable(name = "id") Integer id){
        return classesDao.getAllClasses(id);
}

    @PostMapping("addclass")
    public Response addClasses(@RequestBody Classes classes){
        return  response.operationJudge(classesDao.addClasses(classes),"添加成功!","添加失败!");
    }
    @CrossOrigin
    @GetMapping("clstudents/{id}")
    public List<ClassStudent> getClassAllStudent(@PathVariable(name = "id")Integer id){
        return classesDao.getClassAllStudent(id);
    }

    @GetMapping("searchstu/{id}")

    public JSONObject getStudentQueryById(@PathVariable(name = "id")Integer id){
        return userDao.getQueryStudent(id);
    }

    @Transactional(rollbackFor = {Error.class,RuntimeException.class})
    @PostMapping("addclastu")
    public Response addClaStuend(@RequestBody JSONObject object){
        if (classesDao.isExistenceClass(object.getInteger("sid"),object.getInteger("cid"))==null){
            classesDao.addClassNumber(object.getInteger("cid"));
            return response.operationJudge(classesDao.addClassStudent(object),"添加成功!","添加失败!");
        }
        else{
            return new Response(400,"该学生已存在");
        }

    }

    @PostMapping("delclastu")
    public Response delClassStudent(@RequestBody JSONObject object){
       return   response.operationJudge(classesDao.deleteClassStudent(object.getInteger("sid"),object.getInteger("cid")),"删除成功!",
                "删除失败!");
    }

    @Transactional
    @PostMapping("delclass/{id}")
    public Response delClass(@PathVariable("id")Integer id){
        System.out.println("删除数量:"+ classesDao.deleteClassAllStudent(id));
        return response.operationJudge(classesDao.deleteClass(id),"删除成功!","删除失败!");
    }

    @PostMapping("updatecla")
    public Response updateClass(@RequestBody JSONObject object){
        return response.operationJudge(classesDao.update(object),"修改成功!","修改失败!");
    }

    @PostMapping("addtp")
    public Response addTestPaper(@RequestBody JSONObject object){
        return response.operationJudge(tpDao.addTestPaper(object),"添加成功！","添加失败！");
    }

    @GetMapping("tps/{uid}")
    public List<TestPaper> getTestPaperByUid(@PathVariable("uid")Integer id){
        return tpDao.getTestPaperByUid(id);
    }

    @PostMapping("deltp/{id}")
    public Response delTestPaperById(@PathVariable("id")Integer id){
        return response.operationJudge(tpDao.delTestPaperById(id),"删除成功!","删除失败！");
    }

    @PostMapping("updatetp")
    public Response updateTpById(@RequestBody JSONObject object){
        return  response.operationJudge(tpDao.updateTestPaperById(object),"修改成功!","修改失败!");
    }
    @GetMapping("stus/{str}")
    public List<JSONObject> getStudentsByKeyString(@PathVariable("str")String str,@RequestParam("cid")Integer cid){
        List<JSONObject> lists = userDao.getSomeStudentByKeyString(str);
        for (JSONObject obj:lists
             ) {
            if(classesDao.isExistenceClass(obj.getInteger("_id"),cid)!=null){
                obj.put("_exist",true);
            }
            else{
                obj.put("_exist",false);
            }
        }
        return lists;
    }
    @PostMapping("butchaddcs")
    public Response butchAddClassStudent(@RequestBody JSONObject s){
        return new Response(200,"添加了"+classesDao.butchAddStudents(s.getJSONArray("data"))+"条数据");
    }

    @PostMapping("uptpcontent/{id}")
    public Response updatePaperContent(@PathVariable("id")Integer id, @RequestBody JSONObject object){
        return response.operationJudge(tpDao.updateTestPaperContent(object.getString("data"),id),"更新成功!","更新失败！");
    }

    @GetMapping("gtp/{id}")
    public TestPaper getTestPaperById(@PathVariable("id")Integer id){
        return tpDao.getTestPaperById(id);
    }

    @Autowired
    ExamDao examDao;
    @PostMapping("examadd")
    public Response insertExam(@RequestBody JSONObject object){
        return response.operationJudge(examDao.insertExam(object),"添加成功!","添加失败");
    }

    @GetMapping("exams/{id}")
    public List<Exam> getExams(@PathVariable("id")Integer id){
        return examDao.getAllExamByTeacherUid(id);
    }
    @PostMapping("updatexam")
    public Response updateExam(@RequestBody JSONObject object){
        return response.operationJudge(examDao.updateExam(object),"修改成功！","修改失败！");
    }
    @GetMapping("exam/{id}")
    public Exam getExam(@PathVariable("id")Integer id){
        return examDao.getExamById(id);
    }

   @GetMapping("stuexams/{id}")
   public List<MyExam> getStudentExams(@PathVariable("id")Integer id){
       return examDao.getStudentAllExamsBySid(id);
   }

   @Autowired
    StudentExamMapper studentExamMapper;
   @PostMapping("addstuexam")
    public Response insertStudentExam(@RequestBody JSONObject object){
       return response.operationJudge(studentExamMapper.insertStudentExam(object),"插入成功!","插入失败！");
   }

   @GetMapping("stuexam")
    public StuExam getStudentExam(@RequestParam("sid")Integer sid,@RequestParam("eid")Integer eid){
       return studentExamMapper.getStuExam(sid, eid);
   }

   @PostMapping("savestuexam")
   public Response saveStudentExam(@RequestBody JSONObject object){
       return response.operationJudge(studentExamMapper.saveStudentSubmitExam(object),"保存成功!","保存失败！");
   }

   @Autowired
    AutoCheckSubject autoCheckSubject;

   @Transactional
   @GetMapping("submitexam")
    public Response StudentSubmitExam(@RequestParam("sid")Integer sid,@RequestParam("eid")Integer eid){
       StuExam stuExam =   studentExamMapper.getStuExam(sid, eid);
      List<Result> results =  autoCheckSubject.checkAll(stuExam);
      return response.operationJudge(studentExamMapper.updateExamResult(JSONObject.toJSONString(results),autoCheckSubject.GetSumScore(results),stuExam.getId()),"" +
              "提交成功！","提交失败!");
   }

   @Autowired
   LoadStudentSubjects loadStudentSubjects;
   @Transactional
   @CrossOrigin
   @GetMapping("submits/{id}")
    public List<StudentSubjectives> getSSj(@PathVariable("id")Integer id){
       List<StuExam> stuExams = studentExamMapper.getExamAllStudent(id);
       return loadStudentSubjects.getSSj(stuExams);
   }
    @Autowired
    GetExamSubject getExamSubject;
    @Transactional
   @PostMapping("resultex")
    public Response updateResult(@RequestBody JSONObject object){
        studentExamMapper.updatResult(object);

        StuExam stuExam = studentExamMapper.getStuExam(object.getInteger("sid"),object.getInteger("eid"));
        List<Result> results =  autoCheckSubject.checkAll(stuExam);

        return response.operationJudge(studentExamMapper.updateExamResult(JSONObject.toJSONString(autoCheckSubject.checkAll(stuExam)),
                autoCheckSubject.GetSumScore(results),stuExam.getId()),"" +
                "提交成功！","提交失败!");
   }

   @GetMapping("test")
    public String gexasc(){
       return JSONObject.toJSONString(getExamSubject.getAllExamSubject(studentExamMapper.getStuExam(3, 3)));
   }

   @GetMapping("invoite/{code}")
    public Classes getClassByCode(@PathVariable("code")String code){
        return classesDao.getClassByCode(code);
   }

    @Autowired
    UserServer userServer;
   @PostMapping("register")
    public Response registerUser(@RequestBody JSONObject object){
       System.out.println(JSONObject.toJSONString(object));
       return response.operationJudge(userServer.register(object),"注册成功!请重新登录","注册失败！请联系管理员");
   }

   @GetMapping("getUserNameById/{id}")
    public String getUserNameById(@PathVariable("id")Integer id){
       return userDao.getUserNameById(id).getString("_username");
   }
   @Autowired
    UserInfoDao infoDao;
   @PostMapping("adduserinfo")
    public Response addUserInfo(@RequestBody JSONObject object){
       return response.operationJudge(infoDao.insertUserData(object),"添加用户数据成功!","添加用户数据失败!");
   }

   @GetMapping("us/{id}")
   public UserInfo getUserDataById(@PathVariable("id")Integer id){
       return infoDao.getUserData(id);
   }
    @Transactional
   @PostMapping("us")
    public Response updateUserData(@RequestBody JSONObject object){
       userDao.updateUserName(object.getString("no"),object.getInteger("id"));
       return  response.operationJudge(infoDao.updateUserData(object),"更新成功!","更新失败!");
   }
}
