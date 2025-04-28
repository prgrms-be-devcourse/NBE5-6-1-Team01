package com.grepp.nbe561team01.app.model.item;

import com.grepp.nbe561team01.app.model.item.dto.ItemDto;
import com.grepp.nbe561team01.app.model.item.dto.ItemImg;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ItemRepository {

    @Insert("insert into items(ITEM_TYPE, ITEM_NAME, ITEM_PRICE)"
        + "values (#{itemType}, #{itemName}, #{itemPrice})")
    @Options(useGeneratedKeys = true, keyColumn = "item_id", keyProperty = "itemId")
    void insertItem(ItemDto itemDto);

    List<ItemDto> selectAll();

    @Delete("delete from items where ITEM_ID = #{itemId}")
    boolean removeItem(Long itemId);

    ItemDto findById(Long itemId);

    @Update("UPDATE items SET item_type = #{item.itemType}, item_name = #{item.itemName}, item_price = #{item.itemPrice} WHERE item_id = #{itemId}")
    void updateItem(@Param("itemId") Long itemId, @Param("item") ItemDto item);

    @Insert("insert into item_img (item_id, origin_file_name, rename_file_name, save_path)"
    + "values (#{itemId}, #{originFileName}, #{renameFileName},#{savePath})")
    void insertImg(ItemImg itemImg);

    @Update("UPDATE item_img SET origin_file_name = #{itemImg.originFileName}, "
        + "rename_file_name = #{itemImg.renameFileName}, save_path = #{itemImg.savePath} "
        + "WHERE item_img_id = #{itemImgId}")
    void updateItemImg(@Param("itemImgId") Integer itemImgId, @Param("itemImg") ItemImg itemImg);

    @Select("select * from item_img where item_img_id = #{itemImgId}")
    ItemImg findByItemImgId(Integer itemImgId);
}
