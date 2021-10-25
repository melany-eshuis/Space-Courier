function kortsteRoute() 
{
	const begin = document.getElementById("begin").value;
	const eind = document.getElementById("eind").value;
	const adress = "https://spacecourier.azurewebsites.net/getpath/" + begin + "/" + eind;

	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() 
	{
		if(this.readyState == 4 && this.status == 200) 
		{
			const div = document.getElementById("resultaat");
			div.innerHTML = "<p>" + xhr.responseText + "</p>";
		}
	};
	
	xhr.open("GET", adress, true);
	xhr.send();
}

function newMap(){
	allStars = [];
	allConnections = [];

	const xhr = new XMLHttpRequest();
	const adress = "https://spacecourier.azurewebsites.net/newmap";
	
	xhr.onreadystatechange = function() 
	{
		console.log("ready state: ", this.readState);
		console.log("status: ", this.status);
		if(this.readyState == 4 && this.status == 200) 
		{
			connections();
			courierservice();
			initSpaceRaster();
			
			initStars();
			
			setTimeout(() => { drawConnections(); }, 50);
			setTimeout(() => { drawAllStars(); }, 50);
		}
	};
	
	xhr.open("GET", adress, true);
	xhr.send();

	
	
}

function initialize() 
{
	const xhr = new XMLHttpRequest();
  
	xhr.open("GET", "https://spacecourier.azurewebsites.net/initialize", true);
	xhr.send();
}

function courierservice() 
{
	console.log("courierservice")

	const xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() 
	{
		if(this.readyState == 4 && this.status == 200) 
		{
			allStars = JSON.parse(this.responseText);
			connections();
		} 
	}
	xhr.open("GET", "https://spacecourier.azurewebsites.net/courierservice", true);
	xhr.send();
}

function connections() 
{
	console.log("connections")
	const xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function() 
	{
		if(this.readyState == 4 && this.status == 200) 
		{
			allConnections = JSON.parse(this.responseText);    
			initSpaceRaster();
			initStars();
			setTimeout(() => { drawConnections(); }, 50);
			setTimeout(() => { drawAllStars(); }, 50);
		} 
	}
	xhr.open("GET", "https://spacecourier.azurewebsites.net/connections", true);
	xhr.send();
}

function randomSign() 
{
	return Math.random() >= 0.5 ? 1 : -1;
}

const mapCanvas = document.getElementById("galaxyMap");
const mapContext = mapCanvas.getContext("2d");
const starCanvas = document.getElementById("stars");
const starsCTX = starCanvas.getContext("2d");
var allStars = [];
var allConnections = [];

function drawStar(star, mapRatio) 
{
	mapContext.beginPath();
  //x,y,radius,0,2*PI
	var xMultiplier = mapCanvas.width/48;
	var yMultiplier = mapCanvas.width/27;

	mapContext.arc(
		star.x *  xMultiplier,
		star.y *  yMultiplier /2,
		star.r * mapRatio,
		0,
		2 * Math.PI
	);
  
	mapContext.fillStyle = star.hex;
	mapContext.fill();
}

function drawRaster(mapRatio)
{
	//draw horizontal lines
	for( i=0; i<100; i++)
	{
		var multiplier = mapCanvas.width/48;
		mapContext.beginPath();
		mapContext.moveTo(multiplier *i, 0);
		mapContext.lineTo(multiplier *i, mapCanvas.width);
		mapContext.strokeStyle = "#C8C8C8";
		mapContext.lineWidth = 1 * mapRatio;
		mapContext.stroke();
	}
	
  //draw vertical lines
	for( i=0; i<100; i++)
	{
		var multiplier = mapCanvas.height/27;
		mapContext.beginPath();
		mapContext.moveTo(0,multiplier *i);
		mapContext.lineTo(mapCanvas.width,multiplier *i);
		mapContext.strokeStyle = "#C8C8C8";
		mapContext.lineWidth =1 * mapRatio;
		mapContext.stroke();
	}
}

function initSpaceRaster() 
{
	console.log("initSpaceRaster");
	const rect = mapCanvas.getBoundingClientRect();
	mapCanvas.width = rect.width * devicePixelRatio;
	mapCanvas.height = rect.height * devicePixelRatio;
	mapContext.scale(devicePixelRatio, devicePixelRatio);
	const mapRatio = mapCanvas.width / 1920;

	//drawRaster(mapRatio);
	drawAllStars();
}

function initStars() 
{
	console.log("initStars");

	const COLOR_SPACE = "black";
	const COLOR_STARS = "white";
	const STAR_NUM = 200;
	const STAR_SIZE = 0.005;
	const STAR_SPEED = 0.05;
	const rect2 = starCanvas.getBoundingClientRect();
	starCanvas.width = rect2.width * devicePixelRatio;
	starCanvas.height = rect2.height * devicePixelRatio;
	starsCTX.scale(devicePixelRatio, devicePixelRatio);
	const stars = [];
	const starSpeed = STAR_SPEED * starCanvas.width;
	const xv = starSpeed * randomSign() * Math.random();
	const yv = Math.sqrt(Math.pow(starSpeed, 2) - Math.pow(xv, 2)) * randomSign();

	for (let i = 0; i < STAR_NUM; i++) 
	{
		const speedMult = Math.random() * 1.5 + 0.5;
		stars[i] = 
		{
			r: (Math.random() * STAR_SIZE * starCanvas.width) / 2,
			x: Math.floor(Math.random() * starCanvas.width),
			y: Math.floor(Math.random() * starCanvas.height),
			xv: xv * speedMult,
			yv: yv * speedMult,
		};
	}

	let timeDelta, timeLast = 0;
	requestAnimationFrame(loop);

	// set up the animation loop
	function loop(timeNow) 
	{
    // calcualte the time difference
    timeDelta = timeNow - timeLast;
    timeLast = timeNow;
    starsCTX.clearRect(0, 0, starCanvas.width, starCanvas.height);

    // space background
    //starsCTX.fillStyle = COLOR_SPACE;
    //starsCTX.fillRect(0,0, starCanvas.width, starCanvas.height);

    //draw the stars
    starsCTX.fillStyle = COLOR_STARS;
    for (let i = 0; i < STAR_NUM; i++) 
	{
		starsCTX.beginPath();
		starsCTX.arc(stars[i].x, stars[i].y, stars[i].r, 0, Math.PI * 2);
		starsCTX.fill();

		// update the star's x position
		stars[i].x += stars[i].xv * timeDelta * 0.001;

		// reposition the star to the other side if it goes off screen
		if (stars[i].x < 0 - stars[i].r) 
		{
			stars[i].x = starCanvas.width + stars[i].r;
		} 
		else if (stars[i].x > starCanvas.width + stars[i].r) 
		{
			stars[i].x = 0 - stars[i].r;
		}

		// update the star's y position
		stars[i].y += stars[i].yv * timeDelta * 0.001;

		// reposition the star to the other side if it goes off screen
		if (stars[i].y < 0 - stars[i].r) 
		{
		stars[i].y = starCanvas.height + stars[i].r;
		} 
		else if (stars[i].y > starCanvas.height + stars[i].r) 
		{
			stars[i].y = 0 - stars[i].r;
		}
    }

    //call the next frame
    requestAnimationFrame(loop);
	}
}


function drawAllStars()
{
	for(i=0; i< allStars.length; i++)
	{
		drawStar(allStars[i],(mapCanvas.width / 1920));
	}
}


//allConnections[0].firstStar.x
function drawConnections(){
	var multiplierX = mapCanvas.width/48;
	var multiplierY = mapCanvas.height/27;
	for(i=0; i< allConnections.length; i++)
	{
		mapContext.beginPath();
		mapContext.moveTo	(	multiplierX*allConnections[i].firstStar.x,
								multiplierY*allConnections[i].firstStar.y)
		mapContext.lineTo(		multiplierX*allConnections[i].secondStar.x,
								multiplierY*allConnections[i].secondStar.y)
		mapContext.strokeStyle = "darkgray";
		mapContext.lineWidth = 1.5 * (mapCanvas.width / 1920);
		mapContext.stroke();
	}
	
}



courierservice();


//anders laden de Sterren niet >.>



window.addEventListener("resize", drawConnections);
window.addEventListener("resize", initStars);
window.addEventListener("resize", initSpaceRaster);
window.addEventListener("resize", drawAllStars);


