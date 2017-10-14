package com.dairyplant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.dairyplant.db.DBConnection;
import com.dairyplant.model.Material;

/**
 * {@code MaterialDAO} interact with the material table in the database and
 * holds the data using {@link Material} POJO
 *
 */
public class MaterialDAO {

	private static Logger logger = Logger.getLogger(UserDAO.class);
	private static final String TBL_NAME = "material";
	private static final String COL_MATERIAL_ID = "material_id";
	private static final String COL_MATERIAL_NAME = "material_name";
	private static final String COL_MATERIAL_CATEGORY = "material_category";
	private static final String COL_MATERIAL_PRICE = "material_price";

	private String insertQuery = "INSERT INTO " + TBL_NAME + " values(?,?,?,?)";

	private String deleteQuery = "DELETE FROM " + TBL_NAME + " WHERE "
			+ COL_MATERIAL_ID + "=?";

	private String updateQuery = "UPDATE " + TBL_NAME + " SET "
			+ COL_MATERIAL_NAME + "=?," + COL_MATERIAL_CATEGORY + "=?,"
			+ COL_MATERIAL_PRICE + "=?" + " WHERE " + COL_MATERIAL_ID + "=?";

	private String searchMaterial = "SELECT * FROM " + TBL_NAME + " WHERE "
			+ COL_MATERIAL_ID + "=?";

	private String searchAllMaterials = "SELECT * FROM " + TBL_NAME;

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet resultSet;

	/**
	 * {@code add()} adds the material object in material table in the database
	 * 
	 * @param material
	 *            material reference
	 * @throws SQLException
	 */
	public Material add(Material material) {

		Material newMaterial = null;
		try {
			stmt = getStatement(insertQuery);

			stmt.setInt(1, material.getMaterialId());
			stmt.setString(2, material.getMaterialName());
			stmt.setString(3, material.getCategory());
			stmt.setDouble(4, material.getPrice());

			int check = stmt.executeUpdate();
			newMaterial = getMaterial(material.getMaterialId());

			if (check > 0)
				logger.debug("material inserted");
			else
				logger.debug("failed to insert material");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newMaterial;
	}

	/**
	 * Deletes the material from database
	 * 
	 * @param materialId
	 *            unique identifier of a material
	 * @throws SQLException
	 */
	public void delete(int materialId) {

		try {
			stmt = getStatement(deleteQuery);
			stmt.setInt(1, materialId);
			int check = stmt.executeUpdate();

			if (check > 0)
				logger.debug("material deleted with id : " + materialId);
			else
				logger.debug("failed to delete material");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * updates the field in the material object to database
	 * 
	 * @param material
	 *            reference of material
	 * @throws SQLException
	 */
	public Material update(Material material) {

		Material updatedMaterial = null;
		try {
			Material materialInDB = new MaterialDAO().getMaterial(material
					.getMaterialId());
			stmt = getStatement(updateQuery);

			if (material.getMaterialName() == null)
				material.setMaterialName(materialInDB.getMaterialName());

			if (material.getCategory() == null)
				material.setCategory(materialInDB.getCategory());

			if (material.getPrice() == 0)
				material.setPrice(materialInDB.getPrice());

			stmt.setString(1, material.getMaterialName());
			stmt.setString(2, material.getCategory());
			stmt.setDouble(3, material.getPrice());
			stmt.setInt(4, material.getMaterialId());

			int check = stmt.executeUpdate();
			updatedMaterial = getMaterial(material.getMaterialId());
			if (check > 0)
				logger.debug("material updated");
			else
				logger.debug("failed to update material");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updatedMaterial;
	}

	/**
	 * Retrieves the material for provided material id
	 * 
	 * @param materialId
	 *            unique identifier for a material
	 * @return
	 * @throws SQLException
	 */
	public Material getMaterial(int materialId) {

		Material material = new Material();
		try {
			stmt = getStatement(searchMaterial);
			stmt.setInt(1, materialId);
			resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				material.setMaterialId(resultSet.getInt(1));
				material.setMaterialName(resultSet.getString(2));
				material.setCategory(resultSet.getString(3));
				material.setPrice(resultSet.getDouble(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return material;
	}

	/**
	 * Retrives the all material information in the database
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Material> getAllMaterials() {

		Material material = null;
		List<Material> materials = new ArrayList<Material>();
		try {
			stmt = getStatement(searchAllMaterials);
			resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				material = new Material();
				material.setMaterialId(resultSet.getInt(1));
				material.setMaterialName(resultSet.getString(2));
				material.setCategory(resultSet.getString(3));
				material.setPrice(resultSet.getDouble(4));

				materials.add(material);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materials;
	}

	// private method for creating common statement instance
	private PreparedStatement getStatement(String query) throws SQLException {
		con = DBConnection.getConnection();
		PreparedStatement statement = con.prepareStatement(query);
		return statement;
	}
}
