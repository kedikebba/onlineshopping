
/*     Author:
       Getaneh Yilma Letike, Id: 610112       ---------*/

$(document).ready(function() {
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

});
