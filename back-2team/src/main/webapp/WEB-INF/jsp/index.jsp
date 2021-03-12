<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="EUC-KR">
	<title>PETPLE</title>
	<style>
		* {
			margin: 0;
			padding: 0;
			font-family: sans-serif;
		}

		body {
			/* background-image: url('https://cdn.pixabay.com/photo/2018/12/20/16/24/star-3886297_1280.jpg'); */
			background: linear-gradient(to right, #f1cde9, #d3abf3);
		}

		.col-md-12 {
			width: auto;
			margin: 0 auto;
		}

		@keyframes box-ani {
			0% {
				transform: translate(-50px, -30px);
				opacity: 0;
			}

			50% {
				transform: scale(1.2);
			}

			100% {}
		}

		#box {
			background-color: rgb(85, 21, 85);
			display: flex;
			margin-top: 120px;
			border-radius: 15px;
			animation: box-ani 1s linear forwards;
		}

		#box:hover {
			animation-play-state: paused;
			transform: scale(1.03);
		}

		@keyframes petple-ani {
			0% {
				opacity: 0;
			}

			30% {
				letter-spacing: 25px;
			}

			50% {
				opacity: 0;
			}

			75% {
				opacity: 0;
				letter-spacing: 8px;
			}

			90% {
				color: rgb(85, 21, 85);
			}

			100% {
				color: rgb(37, 2, 37);
				font-size: 1.5rem;
			}
		}

		.petple {

			font-weight: bold;
			animation: petple-ani 2s forwards;
			transition: .3s;
			margin-top: -10px;
		}

		.petple:hover {
			transform: scale(1.2);

		}

		.petple span {
			display: inline-block;
			color: rgb(29, 15, 15);
			text-transform: uppercase;
			/* display: block; */
		}

		.petple:hover span {
			-webkit-transform: rotateY(360deg);
			transform: rotateY(360deg);
			transition: .6s;
		}

		.petple:hover span:nth-of-type(1) {
			-webkit-transition-delay: .02s;
			transition-delay: .02s;
		}

		.petple:hover span:nth-of-type(2) {
			-webkit-transition-delay: .04s;
			transition-delay: .04s;
		}

		.petple:hover span:nth-of-type(3) {
			-webkit-transition-delay: .06s;
			transition-delay: .06s;
		}

		.petple:hover span:nth-of-type(4) {
			-webkit-transition-delay: .08s;
			transition-delay: .08s;
		}

		.petple:hover span:nth-of-type(5) {
			-webkit-transition-delay: .10s;
			transition-delay: .10s;
		}


		@media (max-width: 1280px) {
			body {}
		}

		@media (max-width: 1024px) {
			body {}
		}

		@media (max-width: 960px) {
			body {}
		}

		@media (max-width: 768px) {
			body {}
		}

		@media (max-width: 576px) {
			body {}
		}

	</style>
</head>

<body>
	<div class="col-md-12">
		<div class="row">
			<center>
				<div class="col-md-6">
					<a href="http://i02a202.p.ssafy.io/loading?email=${userEmail}" role="button" class="btn btn-primary">
						<!-- <img id="box" src="http://i02a202.p.ssafy.io:8080/petple/static/img/default.png"></a> -->
						<div class="image_wrapper"></div>
						<img id="box" src="http://52.78.132.70:8080/petple/static/img/default.png"></a>
						</div>
					<br>
					<!-- <div class="petple">Welcome To PETPLE</div> -->
					<div class="petple"><span class="text1">Welcome&nbsp;</span><span class="text1">To&nbsp;</span><span class="text2">PETPLE</span></div>
				</div>
			</center>
		</div>
	</div>
</body>

</html>