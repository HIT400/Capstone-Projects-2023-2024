function generateID(last_id){
    let new_id = parseInt(last_id,10) + 1;
    let placement_id = `PL_${new_id}`
    console.log(placement_id);
    return placement_id;
}

module.exports = {
    generateID
}