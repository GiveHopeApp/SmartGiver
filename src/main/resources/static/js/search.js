/**
 * Created by David on 2/25/17.
 */
"use strict";
(function () {

    //Global variables
    var html = "";

    //The event listener for the search bar
    $("#searchTerm").keyup(function () {

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
                html = "We didn't find anything..."
            }

            $(".table-content").html(html);
        });

        //Clears the html if there is a failure
        request.fail(function () {

            html = "";

            $(".table-content").html(html);
        });

    });


    $("button").click(function () {

        //Change active button class to clicked button
        $('button').removeClass('active');
        $(this).addClass('active');

        //Change th to category clicked
        $('th').text($(this).text());

        //Make charities list visible
        $('.charities-wrapper').css('display', 'block');

        //Generate the url
        var url = "/category/" + $(this).text() + ".json";

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
                html = "<tr><td>"+ "We didn't find anything..." + "</td>></tr>>"
            }

            $(".table-content").html(html);
        });

        //Clears the html if there is a failure
        request.fail(function () {

            html = "";

            $(".table-content").html(html);
        });
    })

})();
