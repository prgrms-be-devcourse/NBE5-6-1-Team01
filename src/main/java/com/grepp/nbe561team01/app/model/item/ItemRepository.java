package com.grepp.nbe561team01.app.model.item;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ItemRepository {

    @Insert("insert into items(ITEM_TYPE, ITEM_NAME, ITEM_PRICE)"
        + "values (#{itemType}, #{itemName}, #{itemPrice})")
    void insertItem(ItemDto itemDto);

    @Select("select * from items")
    List<ItemDto> selectAll();

    @Delete("delete from items where ITEM_ID = #{itemId}")
    boolean removeItem(Long itemId);

    @Select("select * from items where item_id = #{itemId}")
    ItemDto findById(Long itemId);

    @Update("UPDATE items SET item_type = #{item.itemType}, item_name = #{item.itemName}, item_price = #{item.itemPrice} WHERE item_id = #{itemId}")
    void updateItem(@Param("itemId") Long itemId, @Param("item") ItemDto item);
}
