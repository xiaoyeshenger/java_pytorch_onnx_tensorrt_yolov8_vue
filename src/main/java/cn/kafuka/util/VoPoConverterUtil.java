package cn.kafuka.util;

/*
 * @Author zhangyong
 * @Description 通过Dozer框架，实现持久层对象Po(与数据库一一对应)与向前端响应的对像Vo相互转换
 * @Date 上午 11:04 2019/3/19 0019
 * @Param
 * @return
 **/

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class VoPoConverterUtil {

    private static DozerBeanMapper dozer = new DozerBeanMapper();


    /*
     * @Author zhangyong
     * @Description 将src对象中的属性值复制到新建的desc对象的同名属性中(一般用于新增)
     * @Date 上午 11:29 2019/3/19 0019
     * @Param src 源对象
     * @Param descClass 目标对象
     * @return
     **/
    public static <T> T copyProperties(Object src, Class<T> descClass)
    {
        DozerBeanMapper dozer = new DozerBeanMapper();
        return (T)dozer.map(src, descClass);
    }

    /*
     * @Author zhangyong
     * @Description 将src对象中的属性值复制到desc对象的同名属性中(一般用于更新)
     * @Date 上午 11:28 2019/3/19 0019
     * @Param src 源对象
     * @Param desc 目标对象
     * @return
     **/
    public static void beanConverter(Object src, Object desc){
        dozer.map(src, desc);
    }

    /*
     * @Author zhangyong
     * @Description copy对象方法，当属性值是null的时候 不赋值，适用于对象修改(一般用于更新)
     * @Date 上午 11:28 2019/3/19 0019
     * @Param src 源对象
     * @Param desc 目标对象
     * @return
     **/
    public static void beanConverterNotNull(final Object src, final Object desc) {

        WeakReference weakReference = new WeakReference(new DozerBeanMapper());
        DozerBeanMapper mapper = (DozerBeanMapper) weakReference.get();
        mapper.addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(src.getClass(), desc.getClass(), TypeMappingOptions.mapNull(false), TypeMappingOptions.mapEmptyString(false));
            }
        });
        mapper.map(src, desc);
        mapper.destroy();
        weakReference.clear();
    }

    /*
     * @Author zhangyong
     * @Description 将srcList中的元素复制到元素类型为descClass的List集合中
     * @Date 上午 11:30 2019/3/19 0019
     * @Param  srcList
     * @return  descClass
     **/
    public static <T> List<T> copyList(@SuppressWarnings("rawtypes") List srcList, Class<T> descClass)
    {
        List<T> descList = new ArrayList<T>();
        if (srcList != null)
        {
            for (Object obj : srcList)
            {
                T t = VoPoConverterUtil.copyProperties(obj, descClass);
                descList.add(t);
            }
        }
        return descList;
    }
}
