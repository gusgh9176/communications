var main = {
    init : function () {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            classification: $('#classification').val(),
            item: $('#item').val(),
            standard: $('#standard').val(),
            price: $('#price').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('유저가 등록되었습니다.');
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    }
};

main.init();
