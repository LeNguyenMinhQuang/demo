(function ($) {
  "use strict";


  // Toast
  // let btnsAddToCart = document.querySelectorAll(".btnAddToCart");
  // btnsAddToCart.forEach((btn) => {
  //   btn.addEventListener("click", (e) => {
  //     e.preventDefault();
  //     let loginBtn = document.getElementById("loginBtn");
  //     if (loginBtn != null) {
  //       $.toast({
  //         heading: "Error",
  //         text: "Login require!",
  //         position: "top-right",
  //         icon: "error"
  //       })
  //       return;
  //     }
  //     const productId = btn.getAttribute("data-product-id");
  //     const token = document.querySelector("meta[name='_csrf']").getAttribute("content")
  //     const header = document.querySelector("meta[name='_crsf_header']").getAttribute("content");
  //     const cartQuantity = document.querySelector("#cartSum");
  //     $.ajax({
  //       url: `${window.location.origin}/api/add-product-to-cart`,
  //       beforeSend: function (xhr) {
  //         xhr.setRequestHeader(header, token);
  //       },
  //       type: "POST",
  //       data: JSON.stringify({ quantity: 1, productId: productId }),
  //       contentType: "application/json",
  //       success: function (response) {
  //         const sum = +response;
  //         cartQuantity.innerText = sum;
  //         $.toast({
  //           heading: "Cart",
  //           text: "Success",
  //           position: "top-right"
  //         })
  //       }

  //     })
  //     console.log("running at main.js");
  //   })


  // })



  // Spinner
  var spinner = function () {
    setTimeout(function () {
      if ($("#spinner").length > 0) {
        $("#spinner").removeClass("show");
      }
    }, 1);
  };
  spinner(0);

  // Fixed Navbar
  $(window).scroll(function () {
    if ($(window).width() < 992) {
      if ($(this).scrollTop() > 55) {
        $(".fixed-top").addClass("shadow");
      } else {
        $(".fixed-top").removeClass("shadow");
      }
    } else {
      if ($(this).scrollTop() > 55) {
        $(".fixed-top").addClass("shadow").css("top", -55);
      } else {
        $(".fixed-top").removeClass("shadow").css("top", 0);
      }
    }
  });

  // Back to top button
  $(window).scroll(function () {
    if ($(this).scrollTop() > 300) {
      $(".back-to-top").fadeIn("slow");
    } else {
      $(".back-to-top").fadeOut("slow");
    }
  });
  $(".back-to-top").click(function () {
    $("html, body").animate({ scrollTop: 0 }, 1500, "easeInOutExpo");
    return false;
  });

  // Testimonial carousel
  $(".testimonial-carousel").owlCarousel({
    autoplay: true,
    smartSpeed: 2000,
    center: false,
    dots: true,
    loop: true,
    margin: 25,
    nav: true,
    navText: [
      '<i class="bi bi-arrow-left"></i>',
      '<i class="bi bi-arrow-right"></i>',
    ],
    responsiveClass: true,
    responsive: {
      0: {
        items: 1,
      },
      576: {
        items: 1,
      },
      768: {
        items: 1,
      },
      992: {
        items: 2,
      },
      1200: {
        items: 2,
      },
    },
  });

  // vegetable carousel
  $(".vegetable-carousel").owlCarousel({
    autoplay: true,
    smartSpeed: 1500,
    center: false,
    dots: true,
    loop: true,
    margin: 25,
    nav: true,
    navText: [
      '<i class="bi bi-arrow-left"></i>',
      '<i class="bi bi-arrow-right"></i>',
    ],
    responsiveClass: true,
    responsive: {
      0: {
        items: 1,
      },
      576: {
        items: 1,
      },
      768: {
        items: 2,
      },
      992: {
        items: 3,
      },
      1200: {
        items: 4,
      },
    },
  });

  // Modal Video
  $(document).ready(function () {
    var $videoSrc;
    $(".btn-play").click(function () {
      $videoSrc = $(this).data("src");
    });
    console.log($videoSrc);

    $("#videoModal").on("shown.bs.modal", function (e) {
      $("#video").attr(
        "src",
        $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0"
      );
    });

    $("#videoModal").on("hide.bs.modal", function (e) {
      $("#video").attr("src", $videoSrc);
    });
  });

  // thanh tìm kiếm trang shop
  let searchBtn = document.getElementById("searchBtn");
  let searchValue = document.querySelector("#searchFilter > input");
  searchBtn.addEventListener("click", (e) => {
    e.preventDefault();
    let currentUrl = new URL(window.location.href);
    let searchParams = currentUrl.searchParams;
    console.log(searchParams);

    searchParams.set("page", "1");
    searchParams.set("name", searchValue.value);

    window.location.href = currentUrl.toString();

  })

  // thêm param khi filter ở trang shop
  $("#btnFilter").on("click", (e) => {
    e.preventDefault();
    let factoryList = [];
    let targetList = [];
    let priceList = [];
    let sortValue = document.querySelector("#sortFilter .form-check-input:checked").value;

    let items1 = document.querySelectorAll("#factoryFilter .form-check-input:checked");
    let items2 = document.querySelectorAll("#targetFilter .form-check-input:checked");
    let items3 = document.querySelectorAll("#priceFilter .form-check-input:checked");

    for (let i of items1) {
      factoryList.push(i.value);
    }
    for (let i of items2) {
      targetList.push(i.value);
    }
    for (let i of items3) {
      priceList.push(i.value);
    }

    console.log(factoryList, priceList, targetList, sortValue);

    let currentUrl = new URL(window.location.href);
    let searchParams = currentUrl.searchParams;
    console.log(searchParams);

    searchParams.delete("factory");
    searchParams.delete("target");
    searchParams.delete("price");


    searchParams.set("page", "1");
    searchParams.set("sort", sortValue);
    if (factoryList.length > 0) {
      searchParams.set("factory", factoryList.join(","));
    }
    if (targetList.length > 0) {
      searchParams.set("target", targetList.join(","));
    }
    if (priceList.length > 0) {
      searchParams.set("price", priceList.join(","));
    }

    window.location.href = currentUrl.toString();
  })

  //đọc url và tích vào filter

  let params = new URLSearchParams(window.location.search);

  if (params.has("factory")) {
    let factories = params.get("factory").split(',');
    let factoryFilter = document.querySelectorAll("#factoryFilter .form-check-input");
    Array.from(factoryFilter).map((item, index) => {
      if (factories.includes(item.value)) {
        item.checked = true;
      }
    })
  }

  if (params.has("target")) {
    let targets = params.get("target").split(',');
    let targetFilter = document.querySelectorAll("#targetFilter .form-check-input");
    Array.from(targetFilter).map((item, index) => {
      if (targets.includes(item.value)) {
        item.checked = true;
      }
    })
  }

  if (params.has("sort")) {
    let factories = params.get("sort");
    let factoryFilter = document.querySelectorAll("#sortFilter .form-check-input");
    Array.from(factoryFilter).map((item, index) => {
      item.checked = false;
      if (factories.includes(item.value)) {
        item.checked = true;
      }
    })
  }

  if (params.has("price")) {
    let prices = params.get("price").split(',');
    let priceFilter = document.querySelectorAll("#priceFilter .form-check-input");
    Array.from(priceFilter).map((item, index) => {
      if (prices.includes(item.value)) {
        item.checked = true;
      }
    })
  }



  // // Product Quantity
  // $('.quantity button').on('click', function () {
  //     var button = $(this);
  //     var oldValue = button.parent().parent().find('input').val();
  //     if (button.hasClass('btn-plus')) {
  //         var newVal = parseFloat(oldValue) + 1;
  //     } else {
  //         if (oldValue > 0) {
  //             var newVal = parseFloat(oldValue) - 1;
  //         } else {
  //             newVal = 0;
  //         }
  //     }
  //     button.parent().parent().find('input').val(newVal);
  // });

  $(".quantity button").on("click", function () {
    console.log("clicked")
    let change = 0;
    var button = $(this);
    var oldValue = button.parent().parent().find("input").val();

    if (button.hasClass("btn-plus")) {
      var newVal = parseFloat(oldValue) + 1;
      change = 1;
    } else {
      if (oldValue > 1) {
        var newVal = parseFloat(oldValue) - 1;
        change = -1;
      } else {
        newVal = 1;
      }
    }

    var input = button.parent().parent().find("input");
    input.val(newVal);

    //update in checkout form
    const checkoutindex = input.attr("data-cart-detail-index");
    const el = document.getElementById(`cartDetails${checkoutindex}.quantity`);
    $(el).val(newVal);

    //get price
    const price = input.attr("data-cart-detail-price");
    const id = input.attr("data-cart-detail-id");

    const priceElement = $(`p[data-cart-detail-id='${id}']`);
    if (priceElement) {
      const newPrice = parseFloat(price) * newVal;
      priceElement.text(newPrice);
    }

    //total price
    const totalPriceElement = $("p[data-cart-total-price]");
    console.log(totalPriceElement);

    if (totalPriceElement && totalPriceElement.length) {
      const currentTotal = totalPriceElement
        .first()
        .attr("data-cart-total-price");
      let newTotal = parseFloat(currentTotal);
      if (change === 0) {
        newTotal == parseFloat(currentTotal);
      } else {
        newTotal = change * parseFloat(price) + parseFloat(currentTotal);
      }

      change = 0; //reset sau mỗi lần thay đổi tiền xong

      //update
      totalPriceElement?.each(function (index, element) {
        $(totalPriceElement[index]).text(newTotal);
        $(totalPriceElement[index]).attr("data-cart-total-price", newTotal);
      });
    }
  });
})(jQuery);
