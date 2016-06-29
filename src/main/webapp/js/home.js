$(document).ready(function() {

    var userData = null;

    $.getJSON("rest/user/current", function(data) {
        userData = data.user;
        if (userData.userName == null) {
            window.location.href = "index.html";
        }
    });

    var addArticleButton = $("#add-button");
    addArticleButton.click(function() {
        var articleDataSend = {
            article: {
                title: $("#article-title").val(),
                articleType: $('#category option:selected').text().replace(/ /g, ''),
                text: $("#article-body").val()
            }
        };

        if (!validate()) {
            return;
        }

        $.ajax({
                url: 'rest/article/add',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(articleDataSend)
            })
            .success(function() {
                alert("Added successfully! Now wait for approval!");
                window.location = "home.html";
            })
            .fail(function() {
                alert("Failed to add article!");
            })
            .always(function() {
                $("#add-form").submit(function() {
                    var form = this;
                    setTimeout(function() {
                        $(':submit', form).attr('disabled', true);
                    }, 50);
                });
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

    $('body').on('click', '#approveArticle', function() {
        var approveID = $(this).attr('data-id');
        $.ajax({
                type: "PUT",
                url: "rest/article/update?id=" + approveID
            })
            .success(function() {
                alert("Added successfully!");
                location.reload();
            })
            .fail(function() {
                alert("Failed to add article!");
            });
    });

    $('body').on('click', '#deleteArticle', function() {
        var deleteID = $(this).attr('data-id');
        $.ajax({
                type: "DELETE",
                url: "rest/article/delete?id=" + deleteID
            })
            .success(function() {
                alert("Removed successfully!");
                location.reload();
            })
            .fail(function() {
                alert("Failed to remove article!");
            });
    });

    function validate() {
        var articleTitle = $('#article-title').val(),
            articleBody = $('#article-body').val();

        if (articleTitle === '' || articleBody === '') {
            alert('Incorect data!');
            return false;
        }

        return true;
    }
});
