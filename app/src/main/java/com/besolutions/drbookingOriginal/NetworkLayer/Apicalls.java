package com.besolutions.drbookingOriginal.NetworkLayer;

import android.content.Context;

import com.android.volley.Request;

/**
 *
 * @desc Java Api Calls Contains all the Project Calls
 */

public class Apicalls
{

    private APIRouter apiRouter;

    public Apicalls(Context context, NetworkInterface networkInterface)
    {

        apiRouter = new APIRouter(context,networkInterface);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func User Login
     */

    public void loginUser(final String email, final String password) {

        apiRouter.performRequest(Apiclient.LOGIN_USER.getURL()+"/"+email+"/"+password,Apiclient.LOGIN_USER.getParams(),null,Request.Method.GET,Apiclient.LOGIN_USER.getCode());

    }


    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func User Registration
     */

    public void registerUser(final String Name, final String Email, final String Phone, final String Password)
    {

        apiRouter.performRequest(Apiclient.Register_Uer.getURL()+"/"+Name+"/"+Email+"/"+Phone+"/"+Password,Apiclient.Register_Uer.getParams(),null,Request.Method.GET,Apiclient.Register_Uer.getCode());


    }

    //----------------------------------------------------------------------------------------------



    /**
     *
     * @func Edit User Profile
     */

    public  void forget_password (final String Email)
    {

        apiRouter.performRequest(Apiclient.Forget_Password.getURL()+"/"+Email,Apiclient.Forget_Password.getParams(),null,Request.Method.GET,Apiclient.Forget_Password.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Add a new Ad
     */

    public  void get_all_doctor (final String id)
    {

        apiRouter.performRequest(Apiclient.Get_All_Doctor.getURL()+"/"+id,Apiclient.Get_All_Doctor.getParams(),null,Request.Method.GET,Apiclient.Get_All_Doctor.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void search_result (final String search_text ,final String order_by, final String id)
    {

        apiRouter.performRequest(Apiclient.Search_Result.getURL()+"/"+search_text+"/"+order_by+"/"+id,Apiclient.Search_Result.getParams(),null,Request.Method.GET, Apiclient.Search_Result.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */


    public void add_favorate (final String id , final String doctor_id )
    {

        apiRouter.performRequest(Apiclient.Add_Favorate.getURL()+"/"+id+"/"+doctor_id,Apiclient.Add_Favorate.getParams(),null,Request.Method.GET, Apiclient.Add_Favorate.getCode());

    }



    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void delete_favorate (final String id ,final String doctor_id )
    {

        apiRouter.performRequest(Apiclient.Delete_Favorate.getURL()+"/"+id+"/"+doctor_id,Apiclient.Delete_Favorate.getParams(),null,Request.Method.GET,Apiclient.Delete_Favorate.getCode());

    }


    //----------------------------------------------------------------------------------------------

    /**
     *
     * @func Main Activity Ads
     *
     */

    public void doctor_details (final String doctor_id , final String user_id)
    {

        apiRouter.performRequest(Apiclient.Doctor_Details.getURL()+"/"+doctor_id+"/"+user_id,Apiclient.Doctor_Details.getParams(),null,Request.Method.GET,Apiclient.Doctor_Details.getCode());

    }


    //----------------------------------------------------------------------------------------------



    /**
     *
     * @func Main Activity Ads
     *
     */

    public void rate_doctors (final String user_id,final String doctor_id,final int rate)
    {

        apiRouter.performRequest(Apiclient.Rate_Doctors.getURL()+"/"+user_id+"/"+doctor_id+"/"+rate,Apiclient.Rate_Doctors.getParams(), null,Request.Method.GET,Apiclient.Rate_Doctors.getCode());

    }

    //----------------------------------------------------------------------------------------------


    /**
     *
     * @func Main Activity Ads
     *
     */

    public void all_product ()
    {

        apiRouter.performRequest(Apiclient.All_Product.getURL(),Apiclient.All_Product.getParams(), null,Request.Method.GET,Apiclient.All_Product.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void my_product (final String user_id)
    {

        apiRouter.performRequest(Apiclient.My_Product.getURL()+"/"+user_id,Apiclient.My_Product.getParams(), null,Request.Method.GET,Apiclient.My_Product.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void product_details(final String prduct_id)
    {

        apiRouter.performRequest(Apiclient.Product_Details.getURL()+"/"+prduct_id,Apiclient.Product_Details.getParams(), null,Request.Method.GET,Apiclient.Product_Details.getCode());

    }

    //----------------------------------------------------------------------------------------------





    public void make_order (final String user_id ,final String product_id)
    {

        apiRouter.performRequest(Apiclient.Make_Order.getURL()+"/"+user_id+"/"+product_id,Apiclient.Make_Order.getParams(), null,Request.Method.GET,Apiclient.Make_Order.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void user_profile(final String user_id )
    {

        apiRouter.performRequest(Apiclient.User_Profile.getURL()+"/"+user_id,Apiclient.User_Profile.getParams(), null,Request.Method.GET,Apiclient.User_Profile.getCode());

    }

    //----------------------------------------------------------------------------------------------

    public void my_favorite (final String user_id )
    {

        apiRouter.performRequest(Apiclient.My_Favorite.getURL()+"/"+user_id,Apiclient.My_Favorite.getParams(), null,Request.Method.GET,Apiclient.My_Favorite.getCode());

    }

    //----------------------------------------------------------------------------------------------

    public void edit_profile (final String user_id, final String user_name, final String email, final String phone )
    {

        apiRouter.performRequest(Apiclient.Edit_profile.getURL()+"/"+user_id+"/"+user_name+"/"+email+"/"+phone,Apiclient.Edit_profile.getParams(), null,Request.Method.GET,Apiclient.Edit_profile.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void change_pasword (final String user_id, final String old_password, final String new_password, final String con_password )
    {

        apiRouter.performRequest(Apiclient.Change_Password.getURL()+"/"+user_id+"/"+old_password+"/"+new_password+"/"+con_password,Apiclient.Change_Password.getParams(), null,Request.Method.GET,Apiclient.Change_Password.getCode());

    }

    //----------------------------------------------------------------------------------------------


    public void confirm_reservation (final String user_id, final String doctor_id, final String reservation_id )
    {

        apiRouter.performRequest(Apiclient.Conform_Reservation.getURL()+"/"+user_id+"/"+doctor_id+"/"+reservation_id,Apiclient.Conform_Reservation.getParams(), null,Request.Method.GET,Apiclient.Conform_Reservation.getCode());

    }

    //----------------------------------------------------------------------------------------------




    public void appointments (final String user_id )
    {

        apiRouter.performRequest(Apiclient.Appointments.getURL()+"/"+user_id,Apiclient.Appointments.getParams(), null,Request.Method.GET,Apiclient.Appointments.getCode());

    }

    //----------------------------------------------------------------------------------------------
}