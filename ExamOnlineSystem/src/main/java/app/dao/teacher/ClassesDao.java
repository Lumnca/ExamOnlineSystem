package app.dao.teacher;


import app.datamodel.teacher.ClassStudent;
import app.datamodel.teacher.Classes;
import app.mapper.ClassesMapper;
import app.mapper.StudentMapper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassesDao implements ClassesMapper{
    @Autowired
    ClassesMapper classesMapper;

    @Override
    public List<Classes> getAllClasses(Integer id) {
        return classesMapper.getAllClasses(id);
    }

    @Override
    public int addClasses(Classes classes) {
        return classesMapper.addClasses(classes);
    }

    @Override
    public int addClassNumber(Integer id) {
        return classesMapper.addClassNumber(id);
    }

    @Override
    public int deleteClass(Integer cid) {
        return classesMapper.deleteClass(cid);
    }

    @Override
    public int update(JSONObject object) {
        return classesMapper.update(object);
    }

    @Override
    public List<ClassStudent> getClassAllStudent(Integer cid) {
        return classesMapper.getClassAllStudent(cid);
    }

    @Override
    public int addClassStudent(JSONObject object) {

        return classesMapper.addClassStudent(object);
    }

    @Override
    public int deleteClassStudent(Integer sid,Integer cid) {
        return classesMapper.deleteClassStudent(sid,cid);
    }

    @Override
    public int deleteClassAllStudent(Integer cid) {
        return classesMapper.deleteClassAllStudent(cid);
    }

    @Override
    public JSONObject isExistenceClass(Integer sid, Integer cid) {
        return classesMapper.isExistenceClass(sid, cid);
    }

    @Override
    public Classes getClassByCode(String code) {
        return classesMapper.getClassByCode(code);
    }

    @Transactional
    public int butchAddStudents(JSONArray objects){
        int i = 0;

        for (int j = 0; j < objects.size(); j++) {
            if( classesMapper.isExistenceClass(  objects.getJSONObject(j).getInteger("sid"),objects.getJSONObject(j).getInteger("cid"))==null ){
                classesMapper.addClassStudent(objects.getJSONObject(j));
                classesMapper.addClassNumber(objects.getJSONObject(j).getInteger("cid"));
                i++;
            }
        }
        return i;
    }
}
