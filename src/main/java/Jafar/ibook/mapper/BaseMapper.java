package Jafar.ibook.mapper;



import Jafar.ibook.dtos.BaseDto;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto

 */
public abstract class BaseMapper<E, D extends BaseDto>implements IBaseMapper {
    public abstract E fromDto(D d);

    public abstract D toDto(E e);
}
