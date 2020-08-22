package com.besolutions.drbookingOriginal.NetworkLayer;


import java.util.List;

public enum Apiclient
{

    /**
     * @API
     *
     * ---> 1) URL OF API METHOD
     *
     * ---> 2) ARRAY OF PARAMETERS KEYS
     *
     */

    LOGIN_USER("User/login/54732964/259743", null,1),
    Register_Uer("User/register/54732964/259743", null,2),
    Forget_Password("User/forget_pass/54732964/259743",null,3),
    Get_All_Doctor("Doctor/getAll/54732964/259743",null,4),
    Search_Result("Search/index/54732964/259743",null,5),
    Add_Favorate("User/addFavoriteDoctor/54732964/259743", null,6),
    Delete_Favorate("User/deleteFavoriteDoctor/54732964/259743",null,7),
    Doctor_Details("Doctor/view/54732964/259743", null,8),
    Rate_Doctors("User/addRate/54732964/259743", null,9),
    All_Product("product/getAll/54732964/259743",null,10),
    My_Product("user/getUserProducts/54732964/259743", null,11),
    Product_Details("product/view/54732964/259743", null,12),
    Make_Order("product/buyProduct/54732964/259743", null,13),
    User_Profile("User/view/54732964/259743", null,14),
    My_Favorite("User/getFavoriteDoctors/54732964/259743", null,15),
    Edit_profile("user/editProfile/54732964/259743", null,16),
    Change_Password("User/update_password/54732964/259743", null,17),
    Conform_Reservation("User/make_reservation/54732964/259743", null,18),
    Appointments("User/view_reservations/54732964/259743", null,19);








    //--------------------------------------

    /**
     * @BASE_URL
     */

    String BASE_URL = "http://drbookingsa.com/renova_en/";

    private final String URL;
    private final List<String> params;
    private  final int code;



    Apiclient(String URL, List<String> params, int code)
    {

        this.URL = URL;
        this.params = params;
        this.code = code;
}

    public String getURL()
    {
        return BASE_URL + URL;
    }

    public List<String> getParams()
    {
        return params;
    }

    public int getCode()
    {
        return code;
    }
}
