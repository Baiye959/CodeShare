<template>
    <div class="container1">
        <div class="card">
            <div class="card">
                <div class="card-body">
                    <slot></slot>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

</script>

<style scoped>
.container1 {
    margin-top: 20px;
    margin-bottom: 20px;
    margin-left: 5%;
    margin-right: 5%;
}
.card {
    min-height: 200vh;
    background-color: rgba(250, 250, 250, 0.3);
}
</style>