<template>
    <div class="container">
        <div class="title">
            <div class="number">
                4
            </div>
            <div class="moon">
                <div class="face">
                    <div class="mouth"></div>
                    <div class="eyes">
                        <div class="eye-left"></div>
                        <div class="eye-right"></div>
                    </div>
                </div>
            </div>
            <div class="number">
                4
            </div>
        </div>

        <div class="subtitle">
                Oops. Looks like you took a wrong turn.
                <button>
                    <RouterLink :to="{name: 'home'}" style="text-decoration: none; color: white;">Go Back</RouterLink>
                </button>
         </div>

    </div>
</template>
<script>


export default {

}

</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Lato|Russo+One');

body {
	padding: 0;
	margin: 0;
}
.container {
    padding: 0;
	width: 100%;
    margin: auto;
    position: relative;
    text-align: center;
}
.title > * {
	display: inline-block;
	font-size: 200px;
}
.number {
	text-shadow: 20px 20px 20px rgba(0, 0, 0, 0.2);
	padding: 0 0.2em;
	font-family: 'Russo One', sans-serif;
}
.subtitle {
	font-size: 25px;
	margin-top: 1.5em;
	font-family: "Lato", sans-serif;
	text-shadow: 4px 4px 4px rgba(0, 0, 0, 0.2);
}
button {
	font-size: 22px;
	margin-top: 1.5em;
	padding: 0.5em 1em;
	letter-spacing:1px;
	font-family: "Lato", sans-serif;
	color:white;
	background-color: transparent;
	border:0;
	cursor:pointer;
	z-index:999;
	border:2px solid white;
	border-radius: 5px;
	text-shadow: 4px 4px 4px rgba(0, 0, 0, 0.2);
	transition: opacity 0.2s ease;
}
button:hover {
    opacity:0.7;
}
button:focus {
    outline:0;
}

.moon {
	position: relative;
	border-radius: 50%;
	width: 160px;
	height: 160px;
	z-index: 2;
	background-color: #fff;
	box-shadow: 0 0 10px #fff, 0 0 20px #fff, 0 0 30px #fff, 0 0 40px #fff,
		0 0 70px #fff, 0 0 80px #fff, 0 0 100px #ff1177;
	animation: rotate 5s ease-in-out infinite;
}

.face {
    top: 60%;
    left: 47%;
    position: absolute;
}

.eye-right {
    left: 50px;
}
.eyes {
    position: absolute;
    top: -30px;
    left: -30px;
}

.eye-left {
    border: 4px solid #5c3191;
    width: 30px;
    height: 15px;
    border-bottom-left-radius: 100px;
    border-bottom-right-radius: 100px;
    border-top: 0;
    position: absolute;
}

.eye-left:before {
    content: "";
    position: absolute;
    border-radius: 50%;
    width: 4px;
    height: 4px;
    background-color: #5c3191;
    top: -2px;
    left: -4px;
}
.eye-left:after {
    content: "";
    position: absolute;
    border-radius: 50%;
    width: 4px;
    height: 4px;
    background-color: #5c3191;
    top: -2px;
    left: -4px;
    left: auto;
    right: -4px;
}

.eye-right {
    border: 4px solid #5c3191;
    width: 30px;
    height: 15px;
    border-bottom-left-radius: 100px;
    border-bottom-right-radius: 100px;
    border-top: 0;
    position: absolute;
}

.eye-right:before {
    content: "";
    position: absolute;
    border-radius: 50%;
    width: 4px;
    height: 4px;
    background-color: #5c3191;
    top: -2px;
    left: -4px;
}
.eye-right:after {
    content: "";
    position: absolute;
    border-radius: 50%;
    width: 4px;
    height: 4px;
    background-color: #5c3191;
    top: -2px;
    left: -4px;
    left: auto;
    right: -4px;
}
.mouth {
    border-top-left-radius: 50%;
    border-bottom-right-radius: 50%;
    border-top-right-radius: 50%;
    background-color: #5c3191;
    width: 25px;
    height: 25px;
    position: absolute;
    animation: snore 5s ease-in-out infinite;
    transform: rotate(45deg);
    box-shadow: inset -4px -4px 4px rgba(0, 0, 0, 0.3);
}


/* //keyframes */
@keyframes rotate {
	0%, 100%{
		transform: rotate(-8deg);
	}
	50% {
		transform: rotate(0deg);
	}
}

@keyframes snore {
	0%, 100% {
		transform: scale(1) rotate(30deg);
	}
	50% {
		transform: scale(0.5) rotate(30deg);
		border-bottom-left-radius: 50%;
	}
}


@media screen and (max-width: 580px) {
	.number {
		font-size:100px;
	}
	.subtitle {
font-size:20px;
		padding: 0 1em;
	}
	.moon {
	width: 100px;
		height: 100px;
	}
	.face {
		transform: scale(0.7)
	}
}


</style>

<!-- <template>
    <ContentField>
        404
    </ContentField>
</template>

<script>
import ContentField from '../../components/ContentField.vue'

export default {
    components: {
        ContentField,
    }
}

</script>

<style scoped>
</style> -->
