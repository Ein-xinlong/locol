package com.example.common.router

/**
 * Created by Android Studio.
 * User: Administrator
 * Date: 2021/1/19
 * Time: 11:32
 */
interface RouterConfig {



    /*群组---分类*/
    interface group {
        companion object {
            const val NEED_LOGIN = "needLogin"

            const val OBJ = "obj"

        }
    }
    interface App {
        companion object{
            /*主页*/
            const val ACTIVITY_HOME="/home/HomeActivity"
            /*启动页*/
            const val ACTIVITY_SPLASH="/app/splash"


            const val ACTIVITY_LOGIN = "/login/MainPage"


        }
    }
    interface Service{
        companion object{
            const val SERVICE="/service/json"
            /*我的模块servers路由*/
            const val PERSON_SERVERS= "/personModel/person"
            const val CLASS_SERVERS= "/classModel/class"
            const val ROOM_SERVERS= "/roomModel/room"
        }

    }
}