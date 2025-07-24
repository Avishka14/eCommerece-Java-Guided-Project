async function loadData() {

    const popUp = Notification();

    const response = await fetch("LoadProductData");

    if (response.ok) {

        const json = await response.json();
        if (json.status) {

            console.log(json);

       
        } else {

            popUp.error({
                message: "Something went wrong"
            });

//            window.location = "index.html";
        }

    } else {
        window.location = "index.html";
    }

}








function searchProduct(firstResult) {

    const brand_name = document.getElementById("brand-options")
            .querySelector(".chosen")?.querySelector("a").innerHTML;  //optional chanin

    const condition_name = document.getElementById("condition-options")
            .querySelector(".chosen")?.querySelector("a").innerHTML;  //optional chanin

    const color_name = document.getElementById("color-options")
            .querySelector(".chosen")?.querySelector("a").innerHTML;  //optional chanin

    const storage_value = document.getElementById("storage-options")
            .querySelector(".chosen")?.querySelector("a").innerHTML;  //optional chanin

    const price_range_start = $("#slider-range").slider("value", 0);
    const price_range_end = $("#slider-range").slider("value", 1);




}

