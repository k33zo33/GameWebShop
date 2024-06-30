document.addEventListener('DOMContentLoaded', function() {
    fetch('/roleController/userInfo')
        .then(response => response.json())
        .then(data => {
            const { username, role } = data;

            if (role !== 'GUEST') {
                document.getElementById('userName').textContent = username;
                document.getElementById('userName').style.display = 'inline-block';
                document.getElementById('login').style.display = 'none';
                document.getElementById('logout').style.display = 'inline-block';
                document.getElementById('orders').style.display = 'inline-block';
            } else {
                document.getElementById('login').style.display = 'inline-block';
                document.getElementById('userName').style.display = 'none';
                document.getElementById('orders').style.display = 'none';
                document.getElementById('logs').style.display = 'none';
            }

            if (role === 'ADMIN') {
                document.getElementById('logs').style.display = 'inline-block';
                document.getElementById('addItems').style.display = 'inline-block';
                document.getElementById('addCategories').style.display = 'inline-block';
                document.querySelectorAll('.admin-action').forEach(button => button.style.display = 'inline-block');
            } else if (role === 'USER') {
                document.getElementById('addItems').style.display = 'none';
                document.getElementById('addCategories').style.display = 'none';
                document.querySelectorAll('.admin-action').forEach(button => button.style.display = 'none');
                document.getElementById('search').style.display = 'none';
            }

            if (role === 'GUEST') {
                document.getElementById('cartButton').style.display = 'inline-block';
                document.getElementById('login').style.display = 'inline-block';
                document.querySelectorAll('.admin-action').forEach(button => button.style.display = 'none');
            }
        })
        .catch(error => console.error('Error:', error));
});