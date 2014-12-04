package sba.cajval.mongodb.dao;

import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import sba.cajval.mongodb.domain.Persona;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class PersonaDaoMongoTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		mongoClient = new MongoClient(SERVER_NAME, SERVER_PORT);
		mongoClient.setWriteConcern(WriteConcern.NORMAL);
	}

	@AfterClass
	public static void tearDownClass() {
		mongoClient.close();
	}

	// @Ignore
	@Test
	public void testGetDatabaseNames() {

		List<String> dbs = mongoClient.getDatabaseNames();
		System.out.println("DB names: " + dbs);
	}

	@Ignore
	@Test
	public void testGetCollectionNames() {

		DB db = mongoClient.getDB(DB_NAME);

		Set<String> colls = db.getCollectionNames();
		System.out.println("Collection names: " + colls);
	}

	@Ignore
	@Test
	public void testInsert() {

		Persona persona = createPersona();

		BasicDBObject doc = createDBObject(persona);

		// acquire resources
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);

		WriteResult result = coll.insert(doc);

		// show result
		System.out.println("UpsertedId: " + result.getUpsertedId());
		System.out.println("N: " + result.getN());
		System.out.println("UpdateOfExisting: " + result.isUpdateOfExisting());
		System.out.println("LastConcern: " + result.getLastConcern());
	}

	@Ignore
	@Test
	public void testUpdate() {

		Persona persona = createPersona();

		// update example
		persona.setNombre("Alan II");

		BasicDBObject doc = createDBObject(persona);

		// acquire resources
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);

		BasicDBObject query = new BasicDBObject("apellido", "Kay");

		WriteResult result = coll.update(query, doc);

		// show result
		System.out.println("UpsertedId: " + result.getUpsertedId());
		System.out.println("N: " + result.getN());
		System.out.println("UpdateOfExisting: " + result.isUpdateOfExisting());
		System.out.println("LastConcern: " + result.getLastConcern());
	}

	@Ignore
	@Test
	public void testDelete() {

		// acquire resources
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);

		BasicDBObject query = new BasicDBObject("apellido", "Kay");

		WriteResult result = coll.remove(query);

		// show result
		System.out.println("UpsertedId: " + result.getUpsertedId());
		System.out.println("N: " + result.getN());
		System.out.println("UpdateOfExisting: " + result.isUpdateOfExisting());
		System.out.println("LastConcern: " + result.getLastConcern());
	}

	@Ignore
	@Test
	public void testCursor() {

		// acquire resources
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);

		DBCursor cursor = coll.find();
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	@Ignore
	@Test
	public void testQuery() {

		// acquire resources
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);

		BasicDBObject query = new BasicDBObject("apellido", "Kay");

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	@Ignore
	@Test
	public void testMoreComplexQuery() {

		// acquire resources
		DB db = mongoClient.getDB(DB_NAME);
		DBCollection coll = db.getCollection(COLLECTION_NAME);

		BasicDBObject query = new BasicDBObject("edad", new BasicDBObject(
				"$gte", 60));

		DBCursor cursor = coll.find(query);
		try {
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}
	}

	private Persona createPersona() {

		Persona persona = new Persona();
		persona.setNombre("Alan");
		persona.setApellido("Kay");
		persona.setEdad(65);

		return persona;
	}

	private BasicDBObject createDBObject(Persona persona) {

		BasicDBObject doc = new BasicDBObject("nombre", persona.getNombre()) //
				.append("apellido", persona.getApellido())//
				.append("edad", persona.getEdad());

		return doc;
	}

	private static MongoClient mongoClient; // thread safe resource

	// private static final String SERVER_NAME = "localhost";
	private static final String SERVER_NAME = "da03d.cajval.sba.com.ar";

	// private static final int SERVER_PORT = 27017;
	private static final int SERVER_PORT = 15247;

	private static final String DB_NAME = "local";

	private static final String COLLECTION_NAME = "testData";
}
