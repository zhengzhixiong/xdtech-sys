
$(function(){
    $('#dg').edatagrid({
        url: 'get_users.php',
        saveUrl: 'save_user.php',
        updateUrl: 'update_user.php',
        destroyUrl: 'destroy_user.php'
    });
});
 