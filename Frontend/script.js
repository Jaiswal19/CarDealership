let apiEndpoint = 'http://localhost:8090/Auction/getAllCar';
const loginData = sessionStorage.getItem("loginData");
const pasredResponse = JSON.parse(loginData);
console.log(pasredResponse.email);

let menu = document.querySelector("#menu-btn");
let navbar = document.querySelector(".navbar");

menu.onclick = () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
}

menu.onclick = () => {
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
}

window.onscroll = () => {

    if (window.scrollY > 0) {
        document.querySelector('.header').classList.add('active');
    } else {
        document.querySelector('.header').classList.remove('active');
    }

    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
}

window.onload = () => {

    if (window.scrollY > 0) {
        document.querySelector('.header').classList.add('active');
    } else {
        document.querySelector('.header').classList.remove('active');
    }

    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
}

document.querySelector('.home').onmousemove = (e) => {
    document.querySelectorAll('.home-parallax').forEach(elm => {
        let speed = elm.getAttribute('data-speed');
        let x = (window.innerWidth - e.pageX * speed) / 90;
        let y = (window.innerHeight - e.pageY * speed) / 90;

        elm.style.transform = `translateX(${y}px) translateY(${x}px)`
    })
};

document.querySelector('.home').onmouseleave = () => {
    document.querySelectorAll('.home-parallax').forEach(elm => {
        elm.style.transform = `translateX(0px) translateY(0px)`
    })
};

var swiper = new Swiper(".vehicles-slider", {
    slidesPerView: 1,
    spaceBetween: 20,
    loop: false,
    grabCursor: true,
    centeredSlides: true,
    // autoplay: {
    //     delay: 9500,
    //     disableOnInteraction: false,
    //   },
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    breakpoints: {
        0: {
            slidesPerView: 1,
        },
        768: {
            slidesPerView: 2,
        },
        991: {
            slidesPerView: 3,
        },
    },
});


var swiper = new Swiper(".featured-slider", {
    slidesPerView: 1,
    spaceBetween: 20,
    loop: false,
    grabCursor: true,
    centeredSlides: false,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    breakpoints: {
        0: {
            slidesPerView: 1,
        },
        768: {
            slidesPerView: 2,
        },
        991: {
            slidesPerView: 3,
        },
    },
});


//======================== car specification swiper starts here =============================================
let swiperWrapper = document.getElementById('swiperWrapper');

fetch(apiEndpoint)
.then( response=>{
        console.log(response);
        if (!response.ok) {
            // response.body
            return response.text().then(errorMessage => {
                throw new Error(errorMessage);
              });
      }
      return response.json();
    })
    .then(data => {
        //    data=data.json();
        data.forEach(carData => {
            console.log("control coming here")
            let carSlide = document.createElement('div');
            carSlide.classList.add('swiper-slide', 'box');
            carSlide.innerHTML = `
                    <img src="cars_data/vehicle-${carData.image}.png" alt="" height="100px" width="200px">
                    <div class="content">
                        <h3>${carData.carname}</h3>
                        <div class="price"><span>price: </span> ₹${carData.price}/-</div>
                        <p> 
                            new 
                            <span class="fas fa-circle"></span> ${carData.engineCapacity}cc
                            <span class="fas fa-circle"></span> ${carData.fueltype}
                        </p>
                        <button class="view-specs" id="viewBtn${carData.id}">View Specs</button>
                    </div>
                `;

            swiperWrapper.appendChild(carSlide);


            document.querySelector(`#viewBtn${carData.id}`).onclick = () => {

                document.querySelector('#carName').textContent = carData.carname;
                document.querySelector('#price').textContent = `Price: ₹${carData.price}/-`;
                document.querySelector('#engineCapacity').textContent = `Engine Capacity: ${carData.engineCapacity}cc`;
                document.querySelector('#kmsDriven').textContent = `KMs Driven: ${carData.kmsDriven}`;
                document.querySelector('#fuelType').textContent = `Fuel Type: ${carData.fueltype}`;
                document.querySelector('#modelYear').textContent = `Model Year: ${carData.modelYear}`;
                document.querySelector('#torque').textContent = `Transmission: ${carData.transmission}`;
                document.querySelector('#power').textContent = `Power: ${carData.power}`;
                document.querySelector('#mileage').textContent = `Fuel Type: ${carData.mileage}`;



                document.querySelector('.specifications').classList.toggle('active');

            }

            document.querySelector('#close-specs-form').onclick = () => {
                document.querySelector('.specifications').classList.remove('active');
            }
        })
    })
    .catch(error => {
        console.log('Error fetching data:', error.message);


        if (error.response) {
            // The request was made and the server responded with a status code
            // that falls out of the range of 2xx
            console.error('Response data:', error.response.data);
            console.error('Response status:', error.response.status);
            
            // Accessing the custom exception message
            const customExceptionMessage = error.response.data;
            console.error('Custom Exception Message:', customExceptionMessage);
          }
    });

//======================== car specification swiper ends here =============================================


// TEST

// async function check()
// {
//     const response = await fetch(apiEndpoint);
//     if(!response.ok){
//         console.log("oasjdioasjd");
//         const data = await response.text();
//         console.log( data);
//     }
// }


// check()





// //================== Auction list swiper class 1 starts here ================================================================
const swiperWrapper1 = document.getElementById('carList1');
fetch(apiEndpoint)
    .then(response => response.json())
    .then(data => {
        data.slice(0, 4).forEach(carData => {
            let carSlide = document.createElement('div');
            carSlide.classList.add('swiper-slide', 'box');
            carSlide.innerHTML = `
                <img src="cars_data/vehicle-${carData.image}.png" alt="">
                <h3>${carData.carname}</h3>
                <div class="price"><span>Current Bid:</span> ₹${carData.price}</div>
                <span class="dollar">₹</span>
                <input type="number" class="bid-input" id="toggle" min="${carData.price}" placeholder="place your bid amount" step="1000" value="">
                <button  class="btn">Bid Now</button>
                <button class="btn"> Bid History </button>
            `;

            const bidButton = carSlide.querySelector('.btn');

            bidButton.addEventListener('click', function () {

                const carId = carData.id;
                const bidAmountInput = carSlide.querySelector('.bid-input');
                const bidAmount = bidAmountInput.value;
                console.log(carId, bidAmountInput, bidAmount);
                if (bidAmount > parseInt(bidAmountInput.min)) {
                    let requestBody = {
                        carId: carId,
                        maxbid: bidAmount,
                        customerName: carData.carname,
                        email: pasredResponse.email
                    };
                    console.log(requestBody);
                    fetch('http://localhost:8090/Bid/bid', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(requestBody),
                    })
                        .then(data => {
                            document.getElementById("pop-up-detail").innerHTML = "Bid Place Successfull";
                            document.getElementsByClassName('notification-bar')[0].classList.toggle('active');
                            document.getElementById('close-btn').onclick = (event) => {
                                event.preventDefault();
                                document.getElementsByClassName('notification-bar')[0].classList.remove('active');
                                window.location.reload();
                            }

                        })

                        .catch(error => {
                            console.error('Error:', error);
                            alert('Failed to update bid. Please try again.');
                        });
                }
                else {
           
                                document.getElementById("pop-up-detail").innerHTML = "Bid Amount is low Increase you bid";
                                document.getElementsByClassName('notification-bar')[0].classList.toggle('active');
                                window.location.href = "home.html#featured";
                                document.getElementById('close-btn').onclick = (event) => {
                                    event.preventDefault();
                                    document.getElementsByClassName('notification-bar')[0].classList.remove('active');
                    }
                }
            });



            
        














            swiperWrapper1.appendChild(carSlide);
        });
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
// //============================= swiper class 1 ends here ==============================







//============================== swiper class 2 starts here ================================================
const swiperWrapper2 = document.getElementById('carList2');
fetch(apiEndpoint)
    .then(response => response.json())
    .then(data => {
        // console.log(data);
        data.slice(4, 8).forEach(carData => {
            let carSlide = document.createElement('div');
            carSlide.classList.add('swiper-slide', 'box');
            carSlide.innerHTML = `
                <img src="cars_data/vehicle-${carData.image}.png" alt="">
                <h3>${carData.carname}</h3>
                <div class="price"><span>Current Bid:</span> ₹${carData.price}</div>
                <span class="dollar">₹</span>
                <input type="number" class="bid-input" id="toggle" min="${carData.price}" placeholder="place your bid amount" step="1000" value="">
                <button class="btn">Bid Now</button>
            `;


            const bidButton = carSlide.querySelector('button');

            bidButton.addEventListener('click', function () {

                const carId = carData.id;
                const bidAmountInput = carSlide.querySelector('.bid-input');
                const bidAmount = bidAmountInput.value;
                console.log(carId, bidAmountInput, bidAmount);
                if (bidAmount > parseInt(bidAmountInput.min)) {
                    let requestBody = {
                        carId: carId,
                        maxbid: bidAmount,
                        customerName: carData.carname,
                        email: pasredResponse.email
                    };
                    console.log(requestBody);
                    fetch('http://localhost:8090/Bid/bid', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(requestBody),
                    })
                        .then(data => {
                            document.getElementById("pop-up-detail").innerHTML = "Bid Place Successfull";
                            document.getElementsByClassName('notification-bar')[0].classList.toggle('active');
                            document.getElementById('close-btn').onclick = (event) => {
                                event.preventDefault();
                                document.getElementsByClassName('notification-bar')[0].classList.remove('active');
                                window.location.reload();
                            }

                        })
                        .catch(error => {
                            console.error('Error:', error);
                            alert('Failed to update bid. Please try again.');
                        });

                }
                else {
                    document.getElementById("pop-up-detail").innerHTML = "Bid Amount is low Increase you bid";
                    document.getElementsByClassName('notification-bar')[0].classList.toggle('active');
                    window.location.href = "home.html#featured";
                    document.getElementById('close-btn').onclick = (event) => {
                        event.preventDefault();
                        document.getElementsByClassName('notification-bar')[0].classList.remove('active');
                    }
                }
            });

            swiperWrapper2.appendChild(carSlide);
        });
    })
    .catch(error => {
        alert(error);
        console.error('Error fetching data:', error);
    });
//========================swiper class 2 ends here =================================










//=========================countdown timer starts here =================================================
let countDownDate = new Date("May 20, 2025 12:48:00").getTime();

let x = setInterval(function () {

    let now = new Date().getTime();

    let distance = countDownDate - now;

    let days = Math.floor(distance / (1000 * 60 * 60 * 24));
    let hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    let seconds = Math.floor((distance % (1000 * 60)) / 1000);

    document.getElementById("countdown").innerHTML = days + "d " + hours + "h "
        + minutes + "m " + seconds + "s ";

    if (distance < 0) {

        featured.style.display = 'none'; clearInterval(x);
        document.getElementById("countdown").innerHTML = "EXPIRED";
        receipt.style.display = 'flex';
    }
}, 1000);
//==============================//countdown timer starts here =======================================        






//=====================generate receipt button============================
document.querySelector('#receipt').onclick = () => {
    document.querySelector('.receipt-form-container').classList.toggle('active');
}

document.querySelector('#close-receipt-form').onclick = () => {
    document.querySelector('.receipt-form-container').classList.remove('active');
    // window.location.reload();
}


document.addEventListener('DOMContentLoaded', function () {
    // Close form event
    document.getElementById('close-receipt-form').addEventListener('click', function () {
        document.querySelector('.receipt-form-container').style.display = 'none';
    });



});


//receipt generation from api call ======================================================================
// Fetch data from API
const apiUrl = 'http://localhost:8090//Receipt/getAllReceipt';
const email = pasredResponse.email;

fetch(apiUrl, {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify({ email: email })
})
    .then(response => response.json())
    .then(data => {

        const receiptDataContainer = document.getElementById('receiptData');

        if (data.length > 0) {
            const keys = Object.keys(data[0]);

            keys.slice(0, 3).forEach(key => {
                const spanElement = document.createElement('span');
                spanElement.classList.add('name-span')
                spanElement.innerHTML = `<p>${key} : ${data[0][key]}</p>`;
                receiptDataContainer.appendChild(spanElement);
            });

            data.forEach(receipt => {
                const divElement = document.createElement('div');

                keys.slice(3, 5).forEach(key => {
                    const spanElement = document.createElement('span');
                    spanElement.innerHTML = `<p>${key}: ${receipt[key]}</p>`;
                    divElement.appendChild(spanElement);
                });

                receiptDataContainer.appendChild(divElement);
            });
        } else {
            receiptDataContainer.textContent = 'No receipt data found for the given email.';
        }
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });



//=============================================signout function start =========================================
function logout() {
    sessionStorage.removeItem('loginData');
    window.location.href = "index.html";
}
//===============================================signout function ends ======================================
