/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author Admin
 */

public class StreamData {

    /* Cách đọc hiểu đống bên dưới?
        => Đống bên dưới được viết comment theo cấu trúc sau:
    
        Tên type // mô tả / dữ liệu gửi đi từ client / dữ liệu trả về từ server
     */
    public enum Type {
        // security
        AESKEY, // client gửi aes key cho server / aeskey đã được mã hóa / server không cần phản hồi

        // auth
        LOGIN, // chức năng đăng nhập / email, password / success hoặc failed
        SIGNUP, // chức năng đăng ký / thông tin đăng ký / success hoặc failed
        LOGOUT, 
        UNKNOW_TYPE,
    }
    
    public static Type getType(String typeName) {
        Type result = Type.UNKNOW_TYPE;

        try {
            result = Enum.valueOf(StreamData.Type.class, typeName);
        } catch (Exception e) {
            System.err.println("Unknow type: " + e.getMessage());
        }

        return result;
    }

    public static Type getTypeFromData(String data) {
        String typeStr = data.split(";")[0];
        return getType(typeStr);
    }
}
