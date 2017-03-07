/**
 * Created by David on 2/25/17.
 */
"use strict";
(function () {

    //Global variables
    var html = "";

    //The event listener for the search bar
    $("#searchTerm").keyup(function () {

        //Changing th to match user input
        $("th").text('Charities for ' + '"' + $(this).val() + '"');

        //Makes the search term the path variable for use by the controller
        var url = "/" + $(this).val() + ".json";

        //The ajax request to the search controller
        var request = $.ajax ({
            url: url
        });

        //Generates the html for the results
        request.done(function (charities) {

            html = "";

            charities.forEach(function (charity) {

                html += "<tr>" +
                            "<td>" +
                                "<a href='/charities/" + charity.id + "'>" + charity.charityName + "</a>" +
                            "</td>" +
                        "</tr>"
            });

            if (charities.length == 0) {
                html = "<tr>" +
                            "<td>"+ "We didn't find anything..." + "</td>" +
                        "</tr>"
            }

            $(".table-content").html(html);
        });

        //Clears the html if there is a failure
        request.fail(function () {

            html = "";

            $(".table-content").html(html);
        });

    });


    $(".fourth, .half").click(function () {

        //Change active button class to clicked button
        $(".fourth > .text-category, .half > .text-category").removeClass("active");
        $(this).children().last().addClass("active");

        //Change th to category clicked
        $("th").text($(this).text());

        //Shrink input if th is too long
        if ($("th").text().length > 43) {
            $("#searchTerm").css("width", "13%")
        } else {
            $("#searchTerm").css("width", "15.5%")
        }

        //Make charities list visible
        $('.charities-wrapper').css("display", "block");

        //Generate the url
        var url = "/category/" + $(this).children().last().children().text() + ".json";

        //The ajax request to the search controller
        var request = $.ajax ({
            url: url
        });

        $('html, body').animate({
            scrollTop: $("#table-div").offset().top
        }, 1000);

        //Generates the html for the results
        request.done(function (charities) {

            html = "";

            charities.forEach(function (charity) {

                html += "<tr>" +
                            "<td>" +
                                "<a href='/charities/" + charity.id + "'>" + charity.charityName + "</a>" +
                            "</td>" +
                        "</tr>"
            });

            if (charities.length == 0) {
                html = "<tr>" +
                            "<td>"+ "We didn't find anything..." + "</td>" +
                        "</tr>"
            }

            $(".table-content").html(html);
        });

        //Clears the html if there is a failure
        request.fail(function () {

            html = "";

            $(".table-content").html(html);
        });


        // //Link button to send you down the page on click
        // window.scroll({
        //     top: 2500,
        //     left: 0,
        //     behavior: 'smooth'
        // });
    })

})();
