package seongju.mybatistest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OneOracleDao {
    String getHostName();
}
