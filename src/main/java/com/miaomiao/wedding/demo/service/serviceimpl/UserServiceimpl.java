package com.miaomiao.wedding.demo.service.serviceimpl;

import com.miaomiao.wedding.demo.dao.CameramanMapper;
import com.miaomiao.wedding.demo.dao.OrderMapper;
import com.miaomiao.wedding.demo.dao.PictureMapper;
import com.miaomiao.wedding.demo.dao.UserMapper;
import com.miaomiao.wedding.demo.entity.*;
import com.miaomiao.wedding.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;


@Service
public class UserServiceimpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CameramanMapper cameramanMapper;
    @Autowired
    PictureMapper pictureMapper;

    @Override
    public JsonVo init(Integer id) {
        return null;
    }

    @Override
    public User mine(Integer id) {
        User mine=userMapper.finduserById(id);
        return mine;
    }

    @Override
    public List<User> findfriend(Integer id) {
        List<User> list=userMapper.findfriend(id);
        return list;
    }

    @Override
    public List<Group> findgroup() {
        List<Group> group=userMapper.findgroup();
        return group;
    }

    @Override
    public List<User> findalluser() {
        List<User> users=userMapper.findalluser();
        return users;
    }

    @Override
    public void updatestatus(User para) {
        userMapper.updatestatus(para);
    }

    @Override
    public User finduserByLogin(User user) {
       return userMapper.finduserByLogin(user);
    }

    @Override
    public Integer insertUser(User user) {
        userMapper.insertUser(user);
        return user.getId();
    }

    @Override
    public Integer insertFriend(Integer userid, Integer friendid) {
        return userMapper.insertFriend(userid,friendid);
    }

    @Override
    public Integer insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    public List<Order> findUserOrder(Integer userid) {
        List list=orderMapper.findUserorder(userid);
        return list;
    }

    @Override
    public List<Order> findAllorder() {
        return orderMapper.findAllorder();
    }

    @Override
    public List<String> freeCameraman() {
        return orderMapper.freeCameraman();
    }

    @Override
    public Integer editorderA(Order order) {
        String camermanname=orderMapper.cameraman(order.getOrderId());
        orderMapper.freecamera(camermanname);
        orderMapper.updateorder(order);
        Integer i=orderMapper.workcamera(order.getOrderCameraman());
        return i;
    }

    @Override
    public Integer editorderB(Order order) {
        String camermanname=orderMapper.cameraman(order.getOrderId());
        orderMapper.freecamera(camermanname);
        Integer i=orderMapper.updateorder(order);
        return i;
    }

    @Override
    public List<Order> findDoneorder() {
        return orderMapper.findDoneorder();
    }

    @Override
    public List<Order> findUndoneorder() {
        return orderMapper.findUndoneorder();
    }

    @Override
    public Integer deleteOrder(Integer orderId) {
        String camermanname=orderMapper.cameraman(orderId);
        orderMapper.freecamera(camermanname);
        Integer i=orderMapper.deleteorder(orderId);
        return i;
    }

    @Override
    public List<CameraMan> allCameraman() {
        return cameramanMapper.allcameraman();
    }

    @Override
    public Integer insertcameraman(CameraMan cameraMan) {
        return cameramanMapper.insertcameraman(cameraMan);
    }

    @Override
    public List<CameraMan> sendcameraman() {
        return cameramanMapper.sendcameraman();
    }

    @Override
    public List<CameraMan> unsendcameraman() {
        return cameramanMapper.unsendcameraman();
    }

    @Override
    public Integer editcameraman(CameraMan cameraMan) {
        return cameramanMapper.editcameraman(cameraMan);
    }

    @Override
    public Integer deletecameraman(Integer id) {
        return cameramanMapper.deletecameraman(id);
    }

    @Override
    public List<Picture> findAllpicture(Integer currpage, Integer pagesize) {
        Integer currindex=(currpage-1)*pagesize;
        return pictureMapper.findallpicture(currindex,pagesize);
    }

    @Override
    public Integer pagecount(Integer pagesize) {
        Integer count=pictureMapper.count();
        Integer i=(count+pagesize-1)/(pagesize);
        return i;
    }

    @Override
    public List<Picture> manageallpicture() {
        return pictureMapper.manageallpicture();
    }

    @Override
    public Integer insertpic(Picture pic) {
        return pictureMapper.insertpic(pic);
    }

    @Override
    public Integer deletePic(Integer id) {
        String filename=pictureMapper.picsrc(id).substring(11);
        String image_path="F:\\喵喵婚纱\\用户上传图片\\"+filename;
        File file =new File(image_path);
        if(file.exists()){
            file.delete();
            System.out.println("文件已成功被删除");
        }
        return pictureMapper.deletePic(id);
    }

    @Override
    public Integer editPic(String pictureName, Integer pictureId) {
        return pictureMapper.editPic(pictureName,pictureId);
    }
}
