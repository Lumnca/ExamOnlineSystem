package app.dao.teacher;


import app.datamodel.teacher.TestPaper;
import app.mapper.TestPaperMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestPaperDao implements TestPaperMapper {
    @Autowired
    TestPaperMapper testPaperMapper;

    @Override
    public int addTestPaper(JSONObject object) {
        return testPaperMapper.addTestPaper(object);
    }

    @Override
    public List<TestPaper> getTestPaperByUid(Integer id) {
        return testPaperMapper.getTestPaperByUid(id);
    }

    @Override
    public int delTestPaperById(Integer id) {
        return testPaperMapper.delTestPaperById(id);
    }

    @Override
    public int updateTestPaperById(JSONObject object) {
        return testPaperMapper.updateTestPaperById(object);
    }

    @Override
    public int updateTestPaperContent(String content, Integer id) {
        return testPaperMapper.updateTestPaperContent(content, id);
    }

    @Override
    public TestPaper getTestPaperById(Integer id) {
        return testPaperMapper.getTestPaperById(id);
    }
}
