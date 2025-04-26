package com.grepp.nbe561team01.app.model.item;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemRepository {

    @Select("select * from items")
    List<ItemDto> selectAll();
}
