function kortsteRoute() {
  const begin = document.getElementById("begin").value;
  const eind = document.getElementById("eind").value;
  const adress = "http://localhost:8082/getpath/" + begin + "/" + eind;

  console.log("Adress is:");
  console.log(adress);

  const xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      const div = document.getElementById("resultaat");
      div.innerHTML = "<p>" + xhr.responseText + "</p>";
    }
  };

  //console.log(this.responseText);

  xhr.open("GET", adress, true);

  xhr.send();
}

function randomSign() {
  return Math.random() >= 0.5 ? 1 : -1;
}

const mapCanvas = document.getElementById("galaxyMap");
const mapContext = mapCanvas.getContext("2d");
const starCanvas = document.getElementById("stars");
const starsCTX = starCanvas.getContext("2d");

function drawPlanet(planet, mapRatio) {
  mapContext.beginPath();
  //x,y,radius,0,2*PI
  mapContext.arc(
    planet.x * mapRatio,
    planet.y * mapRatio,
    planet.r * mapRatio,
    0,
    2 * Math.PI
  );
  mapContext.fillStyle = planet.color;
  mapContext.fill();
}

function drawPlanetConnection(coords, mapRatio) {
  mapContext.beginPath();
  mapContext.moveTo(coords.x1 * mapRatio, coords.y1 * mapRatio);
  mapContext.lineTo(coords.x2 * mapRatio, coords.y2 * mapRatio);
  mapContext.strokeStyle = "#C8C8C8";
  mapContext.lineWidth = 5 * mapRatio;
  mapContext.stroke();
}

function initGalaxy() {
  const rect = mapCanvas.getBoundingClientRect();
  mapCanvas.width = rect.width * devicePixelRatio;
  mapCanvas.height = rect.height * devicePixelRatio;
  mapContext.scale(devicePixelRatio, devicePixelRatio);
  const mapRatio = mapCanvas.width / 1920;

  const planets = [
    { x: 200, y: 100, r: 20, color: "darkred" }, //A
    { x: 100, y: 500, r: 30, color: "yellow" }, //B
    { x: 300, y: 800, r: 40, color: "blue" }, //C
    { x: 800, y: 450, r: 60, color: "purple" }, //D
    { x: 900, y: 100, r: 20, color: "orange" }, //E
    { x: 1100, y: 200, r: 40, color: "red" }, //F
    { x: 1050, y: 500, r: 20, color: "darkblue" }, //G
    { x: 1350, y: 350, r: 40, color: "magenta" }, //H
    { x: 1700, y: 50, r: 30, color: "cyan" }, //I
    { x: 1450, y: 650, r: 50, color: "green" }, //J
  ];

  const planetConnections = [
    { x1: 200, y1: 100, x2: 100, y2: 500 }, //AB
    { x1: 300, y1: 800, x2: 100, y2: 500 }, //BC
    { x1: 200, y1: 100, x2: 800, y2: 450 }, //AD
    { x1: 800, y1: 450, x2: 900, y2: 100 }, //DE
    { x1: 800, y1: 450, x2: 1100, y2: 200 }, //DF
    { x1: 800, y1: 450, x2: 1050, y2: 500 }, //DG
    { x1: 1100, y1: 200, x2: 900, y2: 100 }, //FE
    { x1: 1100, y1: 200, x2: 1700, y2: 50 }, //FI
    { x1: 1100, y1: 200, x2: 1350, y2: 350 }, //FH
    { x1: 1350, y1: 350, x2: 1050, y2: 500 }, //HG
    { x1: 1350, y1: 350, x2: 1450, y2: 650 }, //HJ
    { x1: 1350, y1: 350, x2: 1700, y2: 50 }, //HI
    { x1: 1050, y1: 500, x2: 1450, y2: 650 }, //GJ
  ];

  planetConnections.forEach((conn) => {
    drawPlanetConnection(conn, mapRatio);
  });
  planets.forEach((planet) => {
    drawPlanet(planet, mapRatio);
  });
}

function initStars() {
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

  for (let i = 0; i < STAR_NUM; i++) {
    const speedMult = Math.random() * 1.5 + 0.5;
    stars[i] = {
      r: (Math.random() * STAR_SIZE * starCanvas.width) / 2,
      x: Math.floor(Math.random() * starCanvas.width),
      y: Math.floor(Math.random() * starCanvas.height),
      xv: xv * speedMult,
      yv: yv * speedMult,
    };
  }

  let timeDelta,
    timeLast = 0;
  requestAnimationFrame(loop);

  // set up the animation loop
  function loop(timeNow) {
    // calcualte the time difference
    timeDelta = timeNow - timeLast;
    timeLast = timeNow;
    starsCTX.clearRect(0, 0, starCanvas.width, starCanvas.height);

    // space background
    //starsCTX.fillStyle = COLOR_SPACE;
    //starsCTX.fillRect(0,0, starCanvas.width, starCanvas.height);

    //draw the stars
    starsCTX.fillStyle = COLOR_STARS;
    for (let i = 0; i < STAR_NUM; i++) {
      starsCTX.beginPath();
      starsCTX.arc(stars[i].x, stars[i].y, stars[i].r, 0, Math.PI * 2);
      starsCTX.fill();

      // update the star's x position
      stars[i].x += stars[i].xv * timeDelta * 0.001;

      // reposition the star to the other side if it goes off screen
      if (stars[i].x < 0 - stars[i].r) {
        stars[i].x = starCanvas.width + stars[i].r;
      } else if (stars[i].x > starCanvas.width + stars[i].r) {
        stars[i].x = 0 - stars[i].r;
      }

      // update the star's y position
      stars[i].y += stars[i].yv * timeDelta * 0.001;

      // reposition the star to the other side if it goes off screen
      if (stars[i].y < 0 - stars[i].r) {
        stars[i].y = starCanvas.height + stars[i].r;
      } else if (stars[i].y > starCanvas.height + stars[i].r) {
        stars[i].y = 0 - stars[i].r;
      }
    }

    //call the next frame
    requestAnimationFrame(loop);
  }
}

initStars();
initGalaxy();
window.addEventListener("resize", initStars);
window.addEventListener("resize", initGalaxy);

/////RAMON

// var planetA = {
// 	xCoordinate: 200,
// 	yCoordinate: 100,
// 	radius: 20,
// 	planetColor: "darkred",
// 	drawPlanet(xCoordinate,yCoordinate,radius,planetColor);
// }

////////////////////////////////////////////////////////////////////////

//set up stars

// let sizeQ = 20;
// let positionXX = 0;
// flyingcircle();
// function flyingcircle(){
// 	starsCTX.clearRect(0,0,starCanvas.width, starCanvas.height)
// 	//sizeQ += 0.05;
// 	positionXX += 1;

// 	starsCTX.fillStyle = 'red';
// 	starsCTX.lineWidth = 5;
// 	starsCTX.beginPath();
// 	starsCTX.arc(positionXX,300, sizeQ, 0, Math.PI*2);
// 	starsCTX.closePath();
// 	starsCTX.fill();

// 	if (positionXX == 1800){
// 		positionXX =0;
// 	}
// 	requestAnimationFrame(flyingcircle);
// }
