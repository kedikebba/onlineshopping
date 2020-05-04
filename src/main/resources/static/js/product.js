
$(document).ready(function() {
    //$('.mdb-select').materialSelect();

    // $(".add-to-cart").onload(function () {
    //     $.ajax({
    //             url: "/addToCart",
    //             contentType: 'application/json',
    //             dataType: 'json',
    //             type: "post",
    //             data: JSON.stringify({productId: 0, quantity: 0}),
    //             success: function(data){
    //                 console.log(data);
    //                 $(".cartCount").text(data)
    //                 $(".cartCount").html(data);
    //
    //             },
    //             error: function (error) {
    //                 console.log('error========================================')
    //                 console.log(error);
    //
    //             }
    //         }
    //
    //     );
    //
    // });

    var selected =1;
    $(".select-quantity").change(function () {
        selected = $(this).val();
    });

    $(".add-to-cart").click(function(){
        product_id = $(this).attr("data");

        var contextRoot = "/" + window.location.pathname.split('/')[1];
        // alert(contextRoot);
        $.ajax({
                // url: contextRoot+"/addToCart",
                url: "/addToCart",
                contentType: 'application/json',
                dataType: 'json',
                type: "post",
                data: JSON.stringify({productId: product_id, quantity: selected}),
                success: function(data){
                    console.log(data);
                    $(".cartCount").text(data)
                    $(".cartCount").html(data);

                },
                error: function (error) {
                    console.log('error========================================')
                    console.log(error);

                }
            }

        );
    });


    $(".delete-items").click(function(){
        product_id = $(this).attr("data");

        var contextRoot = "/" + window.location.pathname.split('/')[1];
        // alert(contextRoot);
        $.ajax({
                url: contextRoot+"/removeItem",
                contentType: 'application/json',
                dataType: 'json',
                type: "delete",
                data: JSON.stringify({productId: product_id}),
                success: function(data){
                    // alert("In Delete Item");
                    window.location.reload();
                    console.log(data)
                },
                error: function (error) {
                    console.log('error========================================');
                    console.log(error);

                }
            }

        );
        $("#card"+product_id).hide();
    });


    $(".edit-cart").click(function(){
        product_id = $(this).attr("data");
        var contextRoot = "/" + window.location.pathname.split('/')[1];
        // alert(contextRoot);
        $.ajax({
                // url: contextRoot+"/editCart/"+product_id+"/"+ selected,
                // url: "/addToCart",
                url: contextRoot+"/addToCart",
                contentType: 'application/json',
                dataType: 'json',
                type: "put",
                data: JSON.stringify({productId: product_id, quantity: selected}),
                success: function(data){
                    // alert("In Edit cart");
                    console.log(data);
                    $(".cartCount").html(data);
                    window.location.reload();

                },
                error: function (error) {
                    console.log('error========================================');
                    console.log(error);

                }
            }

        );
    });

    var orderStatus = 1;
    showOptions = false;
    if(!showOptions){
        $(".select-status").hide();
    }
    $(".changeStatusBtn").click(function () {

        showOptions = !showOptions;
        if(!showOptions){
            $(".select-status").hide();
        }
        else {
            $(".select-status").show();
        }
    });
    $(".select-status").change(function () {
        orderStatus = $(this).val();

        order_id = $(this).attr("data");

        var contextRoot = "/" + window.location.pathname.split('/')[1];
        $.ajax({
                url:"/orders/changeStatus?orderId="+order_id+"&status="+orderStatus,
                type: "get",
                success: function(data){
                    window.location.reload();
                },
                error: function (error) {
                    console.log('error========================================')
                    console.log(error);
                    window.location.reload();
                }
            }
        );

    });

    // $("#complete-order").prop("disabled",true);
    //
    // function occurrences(string, substring){
    //     var n=0;
    //     var pos=0;
    //
    //     while(true){
    //         pos=string.indexOf(substring,pos);
    //         if(pos!=-1){ n++; pos+=substring.length;}
    //         else{break;}
    //     }
    //     return(n);
    // }
    // isSameAddress = false;

    // $(".address").change(function () {
    //     address = $("#address").val();
    //     address2 = $("#address2").val();
    //
    //     var count = occurrences(address,",");
    //     var count2 = occurrences(address2,",");
    //
    //     if(count >= 2 && isSameAddress || (count >= 2 && count2 >= 2)){
    //         $("#invalid-feedback").hide();
    //         $("#invalid-feedback2").hide();
    //         $("#complete-order").prop("disabled",false);
    //     }
    //
    //     else{
    //         if(count<2){
    //             $("#invalid-feedback").show();
    //         }
    //         if(count2 < 2 && !isSameAddress){
    //             $("#invalid-feedback2").show();
    //         }
    //
    //         $("#complete-order").prop("disabled",true);
    //
    //     }
    // });



    // $("#same-address").change(function() {
    //     // this will contain a reference to the checkbox
    //     if (this.checked) {
    //         //$("#address").val($("#address").val())
    //         $("#billingAddressContainer").addClass("d-none");
    //         $("#billingAddressContainer").removeClass("d-block");
    //         isSameAddress = true;
    //     } else {
    //         //$("#address").val('')
    //         $("#billingAddressContainer").removeClass("d-none");
    //         $("#billingAddressContainer").addClass("d-block");
    //         isSameAddress = false;
    //     }
    //
    // });

    $("#complete-order").click(function (event) {

        // order_id = $("#orderId").val();
        // address = $("#address").val();
        // if(isSameAddress){
        //     address2 = address;
        // }
        // else{
        //     address2 = $("#address2").val();
        // }

        var contextRoot = "/" + window.location.pathname.split('/')[1];
        $.ajax({
                url: contextRoot+ "/complete",
                contentType: 'application/json',
                dataType: 'json',
                type: "get",
                success: function(data){
                    console.log(data);
                    $(".success").html("Order successfully completed");
                    $(".success").append("Order Number " + data.orderNumber);
                },
                error: function (error) {
                    console.log('error========================================')
                    console.log(error);
                    $(".success").html("Unable to complete order");
                }
            }
        );

    });

    $("#generate-pdf").click(function () {
        doc.save('a4.pdf');
    });

    $("#cancel-order").click(function () {
        var contextRoot = "/" + window.location.pathname.split('/')[1];
        order_id = $(this).attr("data");
        //alert(order_id)
        window.location.href = contextRoot + "/cancelOrder?orderId="+order_id;
    });

});
