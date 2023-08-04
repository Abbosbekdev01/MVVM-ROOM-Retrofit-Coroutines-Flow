package uz.abbosbek.crud_task_2.mepper

import uz.abbosbek.crud_task_2.database.entity.CountryEntity
import uz.abbosbek.crud_task_2.models.Country

fun Country.mapToEntity(): CountryEntity {
    return CountryEntity(
        0,
        this.name.common.toString(),
        this.flags.png.toString(),
        this.area.toString(),
        this.region.toString(),
        this.population.toString()
    )
}