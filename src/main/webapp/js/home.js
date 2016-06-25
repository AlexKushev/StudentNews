$(document).ready(function() {

    var articleData = null,
        userData = null;

    $.getJSON("rest/user/current", function(data) {
        userData = data.user;
        if (userData.userName == null) {
            window.location.href="index.html";
            console.log('penis');
        }
        if (userData.admin === 0) {
            $("[data-tab=tab-3]").remove();
        } 
    });


    var $AddButton = $("#add-button");
    $AddButton.click(function() {
        var oArticleData = {
            article: {
                title: $("#article-title").val(),
                articleType: $('#category option:selected').text().replace(/ /g, ''),
                text: $("#article-body").val()
            }
        };

        $.ajax({
                url: 'rest/article/add',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(oArticleData)
            })
            .success(function() {
                alert("Added successfully!");
            })
            .fail(function() {
                alert("Failed to add article!");
            })
            .always(function() {
                $("#add-form").submit();
            });
    });

    $('#logout-button').click(function() {
        $.ajax({
            url: 'rest/user/logout',
            type: "GET",
            statusCode: {
                204: function() {
                    window.location.replace("index.html");
                }
            }
        });
    });
});

$(document).ready(function() {

    $('body').on('click', '#deleteArticle', function() {
        var deleteID = $(this).attr('data-id');
        $.ajax({
            type: "DELETE",
            url: "rest/article/delete?id=" + deleteID
        })
        .fail(function() {
            alert("Article not removed!");
        })
        .always(function() {
            alert("Article removed!");
            location.reload();
        });
    });
});
