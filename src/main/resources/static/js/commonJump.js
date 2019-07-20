//获取项目根路径
var basePath = window.origin;
//跳转到小说详情页
function jumpNovelInfo(novelId,novelName) {
    layui.use(['layer'],function () {
        var $ = layui.jquery;
        console.log(novelId);
        var path = basePath + "/home/novelInfo?novelId="+novelId;
        //增加点击
        $.ajax({
            url : basePath + '/home/addClick',
            type : 'POST',
            data : {
                'novelId' : novelId
            },
            dataType : 'json',
            success : function (resp) {
                parent.layui.index.openTabsPage(path,novelName);
            },
        });
    })
}

//跳转到分类页面
function jumpNovelType(typeId) {
    console.log(typeId);
    if (typeId === "1"){
        parent.layui.index.openTabsPage(basePath +'/library/cityIndex',"都市小说");
    } else if (typeId === "2"){
        parent.layui.index.openTabsPage(basePath +'/library/fantasy',"玄幻小说");
    }else if (typeId === "3") {
        parent.layui.index.openTabsPage(basePath + '/library/scienceFiction',"科幻小说");
    }else if (typeId === "4") {
        parent.layui.index.openTabsPage(basePath + '/library/militaryHistory',"历史军事");
    } else if (typeId === "5") {
        parent.layui.index.openTabsPage(basePath + '/library/dragon',"武侠小说");
    } else if (typeId === "6") {
        parent.layui.index.openTabsPage(basePath + '/library/athletic',"网游竞技");
    } else if (typeId === "7") {
        parent.layui.index.openTabsPage(basePath + '/library/suspenseParanormal',"悬疑灵异");
    } else if (typeId === "9") {
        parent.layui.index.openTabsPage(basePath + '/library/fairySpiderman',"仙侠小说");
    } else if (typeId === "10") {
        parent.layui.index.openTabsPage(basePath + '/library/other',"其他小说");
    }
}


//加入书架
function addBookCase(novelId,chapterId) {
    layui.use(['layer'],function () {
        var $ = layui.jquery,
            layer = layui.layer;

        $.ajax({
            url : basePath+'/home/addBookCase',
            type : 'POST',
            data : {
                'novelId' : novelId,
                'chapterId' : chapterId
            },
            dataType : 'json',
            success : function (resp) {
                if (resp.code === 0){
                    layer.open({
                        title : '提示',
                        content: '加入书架成功，前往书架？'
                        ,btn: ['确定', '取消']
                        ,yes: function(index){
                            var path = basePath+'/bookCase/index';
                            layer.close(index);
                            parent.layui.index.openTabsPage(path,"我的书架");
                        }
                    });
                } else {
                    layer.msg('加入书架失败，请刷新页面！',{
                        offset : '165px',
                        icon : 2,
                        time : 1200
                    });
                }
            },
            error : function () {
                layer.msg('服务器繁忙，请稍后再试',{
                    offset : '15px',
                    icon : 9,
                    time : 2000
                });
            }
        })
    });
}

//点击了推荐
function setRecommend(novelId,recommendTimes) {
    layui.use(['layer'],function () {
       var $ = layui.jquery,
           layer = layui.layer;

       //先判断当前用户还剩推荐次数
       if (parseInt(recommendTimes) === 0) {
           layer.msg("您今天已经推荐过三次，请明天再来！",{icon:5,time:1200});
           return;
       }
       $.ajax({
           url : basePath+'/home/recommend',
           type : 'POST',
           data : {
               'novelId' : novelId
           },
           dataType : 'json',
           success : function (resp) {
                if (resp.code === 0) {
                    layer.msg("推荐成功！",{icon:1,time:1200});
                }else{
                    layer.msg(resp.msg,{
                        offset : '165px',
                        icon : 2,
                        time : 1200
                    });
                }
           },
           error : function () {
               layer.msg('服务器繁忙，请稍后再试',{
                   offset : '15px',
                   icon : 9,
                   time : 2000
               });
           }
       })
    });
}

function checkIsNull(val) {
    return val === "" || val === undefined || val === null;
}