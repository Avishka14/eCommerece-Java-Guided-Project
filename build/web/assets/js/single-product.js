

async function loadData() {

    const searchParams = new URLSearchParams(window.location.search);

    if (searchParams.has("id")) {
        const productId = searchParams.get("id");
        const response = await fetch("LoadSingleProduct?id=" + productId);

        if (response.ok) {

            const json = await response.json();

            if (json.status) {

                console.log(json);

                document.getElementById("image1").src = "product-images\\" + json.product.id + "\\image1.png";
                document.getElementById("image2").src = "product-images\\" + json.product.id + "\\image2.png";
                document.getElementById("image3").src = "product-images\\" + json.product.id + "\\image3.png";

                document.getElementById("thumb-image1").src = "product-images\\" + json.product.id + "\\image1.png";
                document.getElementById("thumb-image2").src = "product-images\\" + json.product.id + "\\image2.png";
                document.getElementById("thumb-image3").src = "product-images\\" + json.product.id + "\\image3.png";

                document.getElementById("product-title").innerHTML = json.product.title;
                document.getElementById("published-on").innerHTML = json.product.created_at;
                
                document.getElementById("product-price").innerHTML = json.product.price;
                
                document.getElementById("brand-name").innerHTML = json.product.model.brand.name;
                document.getElementById("model-name").innerHTML = json.product.model.name;
                document.getElementById("product-quality").innerHTML = json.product.quality.value;
                document.getElementById("product-stock").innerHTML = json.product.qty;


                document.getElementById("color-border").style.borderColor = "black";
                document.getElementById("color-background").style.backgroundColor = json.product.color.value;

                document.getElementById("product-storage").innerHTML = json.product.storage.value;
                document.getElementById("product-description").innerHTML = json.product.description;


                //add to cart
                const addtoCartMain = document.getElementById("add-to-cart-main");
                addtoCartMain.addEventListener(
                        "click", (e) => {
                    addToCart(json.product.id, document.getElementById("add-to-cart-qty").value);
                    e.preventDefault();
                });

                //add to cart

                //similler-products

                 let similer_product_main = document.getElementById("smiler-product-main");
                 let productHtml = document.getElementById("similer-product");
                 similer_product_main.innerHTML = "";
                 json.productList.forEach(item => {
                     
                     let productCloneHtml = productHtml.cloneNode(true);
                     productCloneHtml.querySelector("#similer-product-a1").href="single-product.html?id="+item.id;
                     productCloneHtml.querySelector("#similer-product-image").src = "product-images\\"+item.id+"\\image1.png";
                     productCloneHtml.querySelector("#similer-product-add-to-cart").addEventListener(
                                              "click", (e) =>{
                                              addToCart(item.id , 1); 
                                              e.preventDefault();
                                            });
                      productCloneHtml.querySelector("#similer-product-a2").href="single-product.html?id="+item.id;                     
                      productCloneHtml.querySelector("#similer-product-title").innerHTML = item.title;
                      productCloneHtml.querySelector("#similer-product-storage").innerHTML = item.storage.value;
                      productCloneHtml.querySelector("#similer-product-price").innerHTML = item.price;
                      
                      productCloneHtml.querySelector("#similer-product-color-border").style.borderColor = "black";
                      productCloneHtml.querySelector("#similer-product-color-background").style.borderColor = item.color.value;
                    
                      similer_product_main.appendChild(productCloneHtml);
                      

          
                });
                        //similler-products

                        $('.recent-product-activation').slick({
                    infinite: true,
                    slidesToShow: 4,
                    slidesToScroll: 4,
                    arrows: true,
                    dots: false,
                    prevArrow: '<button class="slide-arrow prev-arrow"><i class="fal fa-long-arrow-left"></i></button>',
                    nextArrow: '<button class="slide-arrow next-arrow"><i class="fal fa-long-arrow-right"></i></button>',
                    responsive: [{
                            breakpoint: 1199,
                            settings: {
                                slidesToShow: 3,
                                slidesToScroll: 3
                            }
                        },
                        {
                            breakpoint: 991,
                            settings: {
                                slidesToShow: 2,
                                slidesToScroll: 2
                            }
                        },
                        {
                            breakpoint: 479,
                            settings: {
                                slidesToShow: 1,
                                slidesToScroll: 1
                            }
                        }
                    ]
                });


            } else {
                window.location = "index.html";
            }

        } else {

        }

    }


}


function addToCart(productId, qty) {

    console.log(productId);
    console.log(qty);

}