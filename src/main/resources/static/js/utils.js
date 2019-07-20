

//将base64转换成文件
function dataURLtoFile(dataUrl, filename) {
    var arr = dataUrl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bStr = atob(arr[1]), n = bStr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bStr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}

//下载文件
function downloadFile(fileName, content) {
    var aTag = document.createElement("a");
    var blob = new Blob([content]);
    //创建标签文件名，文件内容
    aTag.download = fileName;
    aTag.href = URL.createObjectURL(blob);
    aTag.click();
    //清理缓存
    URL.revokeObjectURL(blob);
}


var chnNumChar = ["零","一","二","三","四","五","六","七","八","九"];
var chnUnitChar = ["","十","百","千"];
var chnUnitSection = ["","万","亿","万亿","亿亿"];

//数字转中文
function NumberToChinese(num){
    var unitPos = 0;
    var strIns = '', chnStr = '';
    var needZero = false;

    if(num === 0){
        return chnNumChar[0];
    }

    while(num > 0){
        var section = num % 10000;
        if(needZero){
            chnStr = chnNumChar[0] + chnStr;
        }
        strIns = SectionToChinese(section);
        strIns += (section !== 0) ? chnUnitSection[unitPos] : chnUnitSection[0];
        chnStr = strIns + chnStr;
        needZero = (section < 1000) && (section > 0);
        num = Math.floor(num / 10000);
        unitPos++;
    }
    return chnStr;
}

function SectionToChinese(section){
    var strIns = '', chnStr = '';
    var unitPos = 0;
    var zero = true;
    while(section > 0){
        var v = section % 10;
        if(v === 0){
            if(!zero){
                zero = true;
                chnStr = chnNumChar[v] + chnStr;
            }
        }else{
            zero = false;
            strIns = chnNumChar[v];
            strIns += chnUnitChar[unitPos];
            chnStr = strIns + chnStr;
        }
        unitPos++;
        section = Math.floor(section / 10);
    }
    return chnStr;
}