$(function(){
	var tab = 'account_number';
	// 选项卡切换
	$(".account_number").click(function () {
		$('.tel-warn').addClass('hide');
		tab = $(this).attr('class').split(' ')[0];
		checkBtn();
        $(this).addClass("on");
        $(".message").removeClass("on");
        $(".form2").addClass("hide");
        $(".form1").removeClass("hide");
        $("#noAccount").removeClass("hide");
    });
	// 选项卡切换
	$(".message").click(function () {
		$('.tel-warn').addClass('hide');
		tab = $(this).attr('class').split(' ')[0];
		checkBtn();
		$(this).addClass("on");
        $(".account_number").removeClass("on");
		$(".form2").removeClass("hide");
		$(".form1").addClass("hide");
        $("#noAccount").addClass("hide");
    });

	//点击没有账号
    $("#noAccount").click(function () {
        $('.tel-warn').addClass('hide');
        tab = $(this).attr('class').split(' ')[0];
        checkBtn();
        $(".message").addClass("on");
        $(".account_number").removeClass("on");
        $(".form2").removeClass("hide");
        $(".form1").addClass("hide");
        $("#noAccount").addClass("hide");
    });

	$('#num').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#pass').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#veri').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#num2').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#veri-code').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	// 按钮是否可点击
	function checkBtn()
	{
		$(".log-btn").off('click');
		if (tab == 'account_number') {
			var inp = $.trim($('#num').val());
			var pass = $.trim($('#pass').val());
			if (inp != '' && pass != '') {
                if (!$('.code').hasClass('hide')) {
                    code = $.trim($('#veri').val());
                    if (code == '') {
                        $(".log-btn").addClass("off");
                    } else {
                        $(".log-btn").removeClass("off");
                        sendBtn();
                    }
                } else {
                    $(".log-btn").removeClass("off");
                    sendBtn();
                }
			} else {
				$(".log-btn").addClass("off");
			}
		} else {
			var phone = $.trim($('#num2').val());
			var code2 = $.trim($('#veri-code').val());
			if (phone != '' && code2 != '') {
				$(".log-btn").removeClass("off");
				sendBtn();
			} else {
				$(".log-btn").addClass("off");
			}
		}
	}

	function checkAccount(username){
		if (username == '') {
			$('.num-err').removeClass('hide').find("em").text('请输入账户');
			return false;
		} else {
			$('.num-err').addClass('hide');
			return true;
		}
	}

	function checkPass(pass){
		if (pass == '') {
			$('.pass-err').removeClass('hide').text('请输入密码');
			return false;
		} else {
			$('.pass-err').addClass('hide');
			return true;
		}
	}

	function checkCode(code){
		if (code == '') {
			$('.tel-warn').removeClass('hide').text('请输入验证码');
			return false;
		} else {
			$('.tel-warn').addClass('hide');
			return true;
		}
	}

	//校验手机号码是否合格
	function checkPhone(phone){
		var status = true;
		if (phone == '') {
			$('.num2-err').removeClass('hide').find("em").text('请输入手机号');
			return false;
		}
		var param = /^1[34578]\d{9}$/;
		if (!param.test(phone)) {
			// globalTip({'msg':'手机号不合法，请重新输入','setTime':3});
			$('.num2-err').removeClass('hide');
			$('.num2-err').text('手机号不合法，请重新输入');
			return false;
		}
		return status;
	}

	//校验验证码是否输入
	function checkPhoneCode(pCode){
		if (pCode == '') {
			$('.error').removeClass('hide').text('请输入验证码');
			return false;
		} else {
			$('.error').addClass('hide');
			return true;
		}
	}

	// 登录点击事件
	function sendBtn(){
		if (tab == 'account_number') {
			$(".log-btn").click(function(){
				// var type = 'phone';
				var username = $.trim($('#num').val());
				var password = $.trim($('#pass').val());
                var code = $.trim($('#veri').val());
				if (checkAccount(username) && checkPass(password)) {
                    if (!$('.code').hasClass('hide')) {
                        if (!checkCode(code)) {
                            return false;
                        }
                    }
					$.ajax({
			            url: '../user/loginCheck',
			            type: 'post',
                        data: {
			            	'username' : username,
							'password' : password,
                            'code' : code
						},
                        async: true,
			            success:function(resp){
			                if (resp.code == 1) { //登录成功
								console.log(resp.msg);
								window.location.href="../user/index";
			                } else if(resp.code == -2) { //账号错误
			                	$(".log-btn").off('click').addClass("off");
			                    $('.num-err').removeClass('hide').find('em').text(resp.msg);
			                    $('.num-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
			                    return false;
			                } else if(resp.code == -1) { //密码错误
			                	$(".log-btn").off('click').addClass("off");
			                    $('.pass-err').removeClass('hide').find('em').text(resp.msg);
			                    $('.pass-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
			                    return false;
			                }else if (resp.code == -3){ //验证码错误
			                    $(".log-btn").off('click').addClass("off");
			                    $('.img-err').removeClass('hide').find('em').text(resp.msg);
			                    $('.img-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
			                    return false;
                            }
			            },
			            error:function(){
			                
			            }
			        });
				} else {
					return false;
				}
			});
		} else {
			$(".log-btn").click(function(){
				// var type = 'phone';
				var phone = $.trim($('#num2').val());
				var phoneCode = $.trim($('#veri-code').val());
				if (checkPhone(phone) && checkPass(phoneCode)) {
					$.ajax({
			            url: '../user/loginByPhone',
			            type: 'post',
                        data: {
			            	'phone' : phone,
							'phoneCode' : phoneCode
						},
                        async: true,
			            success:function(resp){
			                if (resp.code == 1) {
			                	console.log(resp.msg);
                                window.location.href="../user/index";
			                } else if(resp.code == -1) { //验证码超时
			                	$(".log-btn").off('click').addClass("off");
			                    $('.error').removeClass('hide').text(resp.msg);
			                    return false;
			                }else if (resp.code == 0){ //手机号注册失败
                                $(".log-btn").off('click').addClass("off");
                                $('.num2-err').removeClass('hide').text(resp.msg);
                                return false;
							}else if(resp.code == -2){ //验证码输入有误
                                $(".log-btn").off('click').addClass("off");
                                $('.error').removeClass('hide').text(resp.msg);
                                return false;
							}else if(resp.code == -3){ //此号码未发送验证码
                                $(".log-btn").off('click').addClass("off");
                                $('.num2-err').removeClass('hide').text(resp.msg);
                                return false;
							}
			            },
			            error:function(){
			                
			            }
			        });
				} else {
					$(".log-btn").off('click').addClass("off");
					return false;
				}
			});
		}
	}

	// 登录的回车事件
	$(window).keydown(function(event) {
    	if (event.keyCode == 13) {
    		$('.log-btn').trigger('click');
    	}
    });


	//发送验证码
	$(".form-data").delegate(".send","click",function () {
		var phone = $.trim($('#num2').val());
		if (checkPhone(phone)) {
				$.ajax({
		            url: '../user/loginGetCode',
		            type: 'post',
		            data: {
		            	'phone' : phone
					},
		            async: true,
		            success:function(resp){
		                if (resp.code == 0) {
		                    console.log(resp.msg);
		                } else {
		                    console.log(resp.msg);
		                }
		            },
		            error:function(){
		                
		            }
		        });
	       	var oTime = $(".form-data .time"),
			oSend = $(".form-data .send"),
			num = parseInt(oTime.text()),
			oEm = $(".form-data .time em");
		    $(this).hide();
		    oTime.removeClass("hide");
		    var timer = setInterval(function () {
		   	var num2 = num-=1;
	            oEm.text(num2);
	            if(num2==0){
	                clearInterval(timer);
	                oSend.text("重新发送验证码");
				    oSend.show();
	                oEm.text("60");
	                oTime.addClass("hide");
	            }
	        },1000);
		}
    });



});