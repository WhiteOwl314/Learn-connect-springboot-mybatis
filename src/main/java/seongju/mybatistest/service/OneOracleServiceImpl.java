package seongju.mybatistest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seongju.mybatistest.dao.OneOracleDao;

@Slf4j
@Service
public class OneOracleServiceImpl implements OneOracleService{

    @Autowired
    private OneOracleDao oneOracleDao;

    @Override
    public String getHostName() {
        return this.oneOracleDao.getHostName();
    }
}
