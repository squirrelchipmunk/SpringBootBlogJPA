let index = {
	init:function(){
		$('#btn-save').on('click',()=>{  // ()=>{} this를 바인딩하기 위해서 사용
			this.save();
		});
		/*$('#btn-login').on('click',()=>{  
			this.login();
		});*/
	},
	save:function() {
		let data = {
			title:$('#title').val(),
			content:$('#content').val()
		};
	
		//console.log(data);
		
		// ajax 호출 시 default는 비동기 호출
		$.ajax({
			//회원가입 요청
			type: 'post',
			url: '/api/board',
			data: JSON.stringify(data),
			contentType: 'application/json; charset=utf-8', // body 데이터 타입(MIME)
			dataType:'json' //응답 데이터 json > 자바스크립트 객체로 변경. default json이므로 생략 가능!
		}).done(function(resp){
			//성공 시 실행
			alert('글쓰기 완료');
			location.href='/';
		}).fail(function(err){
			//실패 시 실행
			alert(JSON.stringify(err));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	}
}

index.init();