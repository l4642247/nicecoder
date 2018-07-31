function initDiv(clsname){

    if (clsname == '.fenlei'){
        $.ajax({
            url : 'getTypeShow',
            method : 'get',
            dataType : 'json',
            success : function (data) {
                var html = '';
                var typeList = data.typeList;
                if(typeof(typeList) == "undefined" || typeList == ''){
                    $(clsname).css('display','none');
                }else{
                    html += '<h2>文章分类</h2>';
                    html += '<ul>';
                    $.each(typeList , function(i,item){
                        html += '<li><a href="index.html?type=' + item.id + '">' + item.typename + '（' + item.count + '）</a></li>';
                    });
                    html += '</ul>';
                    //拼接上
                    $(clsname).append(html);
                    $(clsname).css('display','block');
                }

            }
        })
    }

    if (clsname == '.tuijian'){
        $.ajax({
            url : 'getDailyHotShow',
            method : 'get',
            dataType : 'json',
            success : function (data) {
                var html = '';
                var dailyList = data.dailyList;
                if(typeof(dailyList) == "undefined" || dailyList == ''){
                    $(clsname).css('display','none');
                }else{
                    html += '<h2>热门文章</h2>';
                    html += '<ul>';
                    $.each(dailyList , function(i,item){
                        html += '<li><a href="info?id=' + item.id + '">' + item.title + '</a></li>';
                    });
                    html += '</ul>';
                    //拼接上
                    $(clsname).append(html);
                    $(clsname).css('display','block');
                }

            }
        })
    }


}